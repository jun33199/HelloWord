set classpath=

call "c:\bea\weblogic81\server\bin\setWLSEnv.cmd"
rem set classpath=%classpath%;d:\bea\jdk131_08\lib\jcert.jar;d:\bea\jdk131_08\lib\jnet.jar;d:\bea\jdk131_03\lib\jsse.jar
rem set classpath=%classpath%;Z:\tax_vob\bushu\sharelib\log4j-1.2.8.jar

ant -buildfile build.xml


