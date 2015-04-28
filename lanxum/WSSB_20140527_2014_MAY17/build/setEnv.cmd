@echo off
set WL_HOME=c:\bea\weblogic81
set JAVA_HOME=C:\bea\jdk141_05
set SHARE_DIR=E:\huxf_view\Tax_vob\source\V1.1\lib\sharelib
set COMMON_DIR=E:\huxf_view\Tax_vob\source\V1.1\lib\commonlib1.4

set CLASSPATH=

set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\ant.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\optional.jar
set CLASSPATH=%CLASSPATH%;%SHARE_DIR%\xml-apis.jar
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
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\ProxyEjb.jar
set CLASSPATH=%CLASSPATH%;%COMMON_DIR%\KydjService_client.jar

set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\weblogic_sp.jar
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\weblogic.jar
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib\webserviceclient.jar

set CLASSPATH=C:\bea\jdk_141_05\lib\tools.jar;%CLASSPATH%;%SHARE_DIR%;


set PATH=%CD%;%JAVA_HOME%\bin;%PATH%

@echo on
@echo.
@echo CLASSPATH=%CLASSPATH%
@echo.
@echo PATH=%PATH%
@echo.
@echo Your environment has been set.
