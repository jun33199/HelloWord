@echo off
set ANT_OPTS="-Xms2048m -Xmx4096m"
set JAVA_HOME=D:\Zhangjun\jdk1.6
set CATALINA_HOME=D:\Zhangjun\Tomcat 6.0
set SHARE_DIR=D:\pack_script_wssb\bjtax-build\lib\sharelib

set CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\servlet-api.jar;%CATALINA_HOME%\lib\jsp-api.jar;%SHARE_DIR%\ant.jar
set PATH=%CD%;%JAVA_HOME%\bin;%PATH%

@echo on
@echo.
@echo CLASSPATH=%CLASSPATH%
@echo.
@echo PATH=%PATH%
@echo.
@echo Your environment has been set.