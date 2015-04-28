@echo off

SETLOCAL

@rem set JAVA_HOME=%JAVA_HOME% 
set ANT_ARGS=

if not "%OS%"=="Windows_NT" goto win9xStart
:winNTStart
rem On NT/2K grab all arguments at once.  This way we can have args w/ spaces.
set ANT_ARGS=%*
goto doneArgs

:win9xStart
rem Slurp the command line arguments.  This loop allows for an
unlimited number of 
rem agruments (up to the command line limit, anyway).

:setupArgs
if "%1"=="" goto doneArgs
set ANT_ARGS=%ANT_ARGS% %1
shift
goto setupArgs

:doneArgs

set ANTCLASSPATH=%JAVA_HOME%\lib\tools.jar;%CLASSPATH%
set PATH=%JAVA_HOME%\jre\bin;%JAVA_HOME%\bin;%PATH%

java -classpath "%ANTCLASSPATH%" org.apache.tools.ant.Main %ANT_ARGS%

ENDLOCAL
