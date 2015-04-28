@echo off
set WL_HOME=c:\bea\weblogic81
set JAVA_HOME=C:\j2sdk1.4.2_03
set SHARE_DIR=E:\SYAX\subversion\projects\bjtax-lib\trunk\sharelib
set COMMON_DIR=E:\SYAX\subversion\projects\bjtax-lib\trunk\commonlib1.4

#set ANT_OPTS="-Xms512m -Xmx2048m"
set ANT_OPTS="-Xms1024m -Xmx2048m"

set CLASSPATH=

set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\ant.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\optional.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\xml-apis.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\jel_g.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\xercesImpl.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\junit.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\xalan-2.3.1.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\jakarta-poi-1.5.1-final-20020615.jar

set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\classes12.zip
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\log4j-1.2.8.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\struts.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\xerces.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\foundation.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\k_or.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\retroguard.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\jFdfTk.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\FrameWork.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\SafeEngineDeal.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\sfbase.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\zkclasses.jar

set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\SfglService.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\Skh_Service.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\DjService.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\ca4others.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\ca4qm.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\caclasses.jar

set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\weblogic_sp.jar
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\weblogic.jar
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\webserviceclient.jar


set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\commons-beanutils.jar 
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\commons-collections.jar.jar   
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\commons-logging.jar  
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\commons-net-1.3.0.jar

set CLASSPATH=%CLASSPATH%;%SHARE_DIR%


set PATH=%CD%;%JAVA_HOME%\bin;%PATH%

@echo on
@echo.
@echo ANT_OPTS=%ANT_OPTS%
@echo.
@echo CLASSPATH=%CLASSPATH%
@echo.
@echo PATH=%PATH%
@echo.
@echo Your environment has been set.
