package fr.husta.test.assembly;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class JarWithDependenciesTest {

    private static final String FILE_TO_MERGE = "META-INF/services/java.sql.Driver";

    @Test
    public void checkSysProperties() {
        Properties sysProperties = System.getProperties();
//        Set<String> stringPropertyNames = sysProperties.stringPropertyNames();
//        System.out.println("System Properties :");
//        for (String key : stringPropertyNames) {
//            System.out.println(key + " : " + sysProperties.getProperty(key));
//        }

        System.out.println("java.specification.version : " + sysProperties.getProperty("java.specification.version")); // 1.[6|7|8]
        // System.out.println("java.vm.specification.version : " + sysProperties.getProperty("java.vm.specification.version")); // 1.[6|7|8] (1.0 with JDK 6 ?)
        System.out.println("java.version : " + sysProperties.getProperty("java.version")); // 1.[6|7|8].0_[build]
        System.out.println("java.runtime.version : " + sysProperties.getProperty("java.runtime.version")); // full number

    }

    @Test
    public void checkFileInSystemResources() throws IOException {
        // ClassLoader sysCL = ClassLoader.getSystemClassLoader();
        Enumeration<URL> systemResources = ClassLoader.getSystemResources(FILE_TO_MERGE);

        int nbUrls = 0;
        while (systemResources.hasMoreElements()) {
            URL url = systemResources.nextElement();
            System.out.println(url.toString());
            nbUrls++;
        }
        System.out.println("Resource found " + nbUrls + " time(s)");

        // with JDK 6 / 7 :
        // jar:file:/D:/Env_Dev/Java/jdk1.7.0_71/jre/lib/resources.jar!/META-INF/services/java.sql.Driver
        // jar:file:/G:/m2_repository/org/postgresql/postgresql/9.3-1102-jdbc4/postgresql-9.3-1102-jdbc4.jar!/META-INF/services/java.sql.Driver

        // with JDK 8 :
        // jar:file:/G:/m2_repository/org/postgresql/postgresql/9.3-1102-jdbc4/postgresql-9.3-1102-jdbc4.jar!/META-INF/services/java.sql.Driver

    }

    @Test
    public void checkMetaInfContent() throws IOException {
        // check META-INF/services/java.sql.Driver exists
        JarFile jar = new JarFile("target/issue-mvn-assembly-plugin-730-jar-with-dependencies.jar");
        JarEntry entry = jar.getJarEntry(FILE_TO_MERGE);
        if (entry == null) {
            fail("the file '" + FILE_TO_MERGE + "' should exist in jar-with-dependencies");
        }

        // Content should be "org.postgresql.Driver"
        InputStream is = jar.getInputStream(entry);
        String content = IOUtils.toString(is, "UTF-8");
        System.out.println("JDBC Driver found : " + content.substring(0, content.indexOf("\n")));
        assertEquals("org.postgresql.Driver", content.substring(0, content.indexOf("\n")));

        // if test fails and content == "sun.jdbc.odbc.JdbcOdbcDriver",
        // it means it comes from jre/lib/resources.jar!/META-INF/services/java.sql.Driver (which is unwanted)

    }

}
