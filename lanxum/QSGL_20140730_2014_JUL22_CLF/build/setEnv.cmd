set WL_HOME=D:\bea\weblogic81
set JAVA_HOME=D:\bea\jdk141_03

set CLASSPATH=%WL_HOME%\server\lib\weblogic_sp.jar;%WL_HOME%\server\lib\weblogic.jar;%CLASSPATH%

set classpath=%classpath%;E:\zhaob_view\Tax_vob\source\V1.1\lib\sharelib\log4j-1.2.8.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\sharelib\FrameWork.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\jkclasses.jar;E:\QSProject\bushulib\vprinter.jar
set classpath=%classpath%;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\PzglService.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\sharelib\struts.jar
set classpath=%classpath%;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\sfbase.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\shenbaoService.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\zkclasses.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\DjService.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\SfglService.jar;E:\zhaob_view\Tax_vob\source\V1.1\lib\commonlib\sf_classes.jar

set PATH=%WL_HOME%\server\bin;%JAVA_HOME%\bin;%PATH%

@echo.
@echo CLASSPATH=%CLASSPATH%
@echo.
@echo PATH=%PATH%
@echo.
@echo Your environment has been set.
