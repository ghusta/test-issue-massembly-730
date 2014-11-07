package fr.husta.test.assembly;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class JarWithDependenciesTest
{

    @Test
    public void checkMetaInfContent() throws IOException
    {
        // check META-INF/services/java.sql.Driver exists
        JarFile jar = new JarFile("target/issue-mvn-assembly-plugin-2_5-jar-with-dependencies.jar");
        JarEntry entry = jar.getJarEntry("META-INF/services/java.sql.Driver");
        if (entry == null)
        {
            fail("the file 'META-INF/services/java.sql.Driver' should exist in jar-with-dependencies");
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
