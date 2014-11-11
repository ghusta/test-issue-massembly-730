### Resume :

Test case for [MASSEMBLY-730](https://jira.codehaus.org/browse/MASSEMBLY-730).  
Short description : jar-with-dependencies : a file in a dependency is overridden by the same file in JDK / JRE

### List of branches :

* [_master_](https://github.com/ghusta/test-issue-massembly-730/tree/master) : the non working version of maven-assembly-plugin (2.5, 2.5.1).  
* Branch [_fix-for-assembly-2.5.1_](https://github.com/ghusta/test-issue-massembly-730/tree/fix-for-assembly-2.5.1) : the working version of maven-assembly-plugin (2.5, 2.5.1), thanks to upgrade of dependency plexus-io 2.3.4 ([PLXCOMP-270](http://jira.codehaus.org/browse/PLXCOMP-270)).  
* Branch [_assembly-2.4.1_](https://github.com/ghusta/test-issue-massembly-730/tree/assembly-2.4.1) : the working version of maven-assembly-plugin (2.4.1).  
* Branch [_use-snapshot-plugin_](https://github.com/ghusta/test-issue-massembly-730/tree/use-snapshot-plugin) : testing with a snapshot version of the plugin.
