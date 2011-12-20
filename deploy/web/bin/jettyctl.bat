@echo off
if "%OS%"=="Windows_NT" @setlocal
call base_env.bat

@rem jetty所需相关参数
set TMPDIR=%JETTY_SERVER_HOME%\tmp
set JETTY_CONF=%JETTY_SERVER_HOME%\conf\jetty.conf
set JETTY_WEBAPPS=%JETTY_SERVER_HOME%\webapps
set START_INI=%JETTY_SERVER_HOME%\conf\start.ini
set JETTY_ARGS=--ini=%START_INI%
set JETTY_PID=%OUTPUT_HOME%\logs\jetty.pid
set JETTY_LOGS=%JETTY_SERVER_HOME%\logs
@rem jetty必须执行%JAVA%变量,jetty.bat启动时不依赖JAVA_HOME
set JAVA=%JAVA_HOME%\bin\java.exe
@rem jvm参数
set JAVA_OPTIONS=%JAVA_OPTS%
SET JETTY_START=%JETTY_HOME%\start.jar

if "%1"=="start" (
  call :start
  goto end
)
if "%1"=="stop" (
  call :stop
  goto end
)

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal

goto :eof
@rem 准备工作
:prepare
@rem  delete jetty work home dir, then make the jetty work
if exist "%JETTY_SERVER_HOME%" (
    rmdir /s /Q  "%JETTY_SERVER_HOME%"
)
if not exist "%JETTY_SERVER_HOME%" (
    mkdir "%JETTY_SERVER_HOME%"
)

@rem  cp file to jetty server home.
xcopy /y /s /Q "%DEPLOY_HOME%\config\jetty\conf" "%JETTY_SERVER_HOME%\conf\"
xcopy /y /s /Q "%DEPLOY_HOME%\config\jetty\ext"  "%JETTY_SERVER_HOME%\ext\"
@rem  create dir
mkdir  "%OUTPUT_HOME%\logs"
mkdir  "%JETTY_WEBAPPS%"
mkdir  "%JETTY_LOGS%"
mkdir  "%TMPDIR%"
if not exist "%OUTPUT_HOME%\logs\jetty_stdout.log" (
    cd.>"%OUTPUT_HOME%\logs\jetty_stdout.log"
)
@rem  通过复制方式部署output\web.war
rmdir /s/Q  "%JETTY_WEBAPPS%\root.war"
copy /y "%DEPLOY_HOME%\web.war"  "%JETTY_WEBAPPS%\root.war"

goto :eof
@rem 启动jetty
:start
call :prepare
@rem 启动jetty
%JAVA%  -DSTOP.PORT=8009 -DSTOP.KEY=xiaoxiang %JAVA_OPTIONS% -Djetty.logs=%JETTY_LOGS% -Djetty.home=%JETTY_HOME% -Djava.io.tmpdir=%TMPDIR% -jar %JETTY_START% --ini=%START_INI%

goto :eof
@rem 停止jetty
:stop
%JAVA% -DSTOP.PORT=8009 -DSTOP.KEY=xiaoxiang -jar %JETTY_START% --stop

