@echo off
if "%OS%"=="Windows_NT" @setlocal

@rem 查找JAVA_HOME
if "%JAVA_HOME%" == "" goto noJavaHome
if not exist "%JAVA_HOME%\bin\java.exe" goto noJavaHome

if "%1"=="start_httpd" (
  call :start_httpd
  goto end
)
if "%1"=="stop_httpd" (
  call :stop_httpd
  goto end
)
if "%1"=="start_jetty" (
  call :start_jetty
  goto end
)
if "%1"=="stop_jetty" (
  call :stop_jetty
  goto end
)

@rem 没有JDK
:noJavaHome
echo.
echo Error: JAVA_HOME environment variable is not set.
echo.
goto end

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal

goto :eof
@rem 设置一些变量
:prepare_env
call base_env.bat
set BASE_BIN_DIR=%~dp0.
set HOST_NAME=%USERDNSDOMAIN%
set LOG_DIR=%OUTPUT_HOME%/logs
@rem 以前的日志保存
set LOGS_SAVED=%LOG_DIR%/logs_saved
set TIMESTAMP=%date:~0,10%%time:~0,2%
@rem window下/要变成\
set KILLWS_LOG=%LOG_DIR%\killws.log
@rem backup logs
if not exist "%LOGS_SAVED%" (
md "%LOGS_SAVED%"
)
if exist "%KILLWS_LOG%" (
move /y %KILLWS_LOG% %LOGS_SAVED%/killws.log.%TIMESTAMP%
)

goto :eof
@rem 启动apache
:start_httpd
call :prepare_env
@rem 检查日志文件
set APACHE_LOG="%LOG_DIR%\apache_error.log"
if exist %APACHE_LOG% (
move /y %APACHE_LOG% "%LOGS_SAVED%/apache_error.log.%TIMESTAMP%
)
@rem 启动apache
echo  "%HOST_NAME%: starting httpd ..."
%HTTPD_HOME%\bin\httpd.exe -f %BASE_BIN_DIR%\..\config\httpd\httpd.window.conf -w -n "Apache2.2" -k start
echo  "Oook!"
echo  "%HOSTNAME%: reloadws_alone done!"

goto :eof
@rem 停止apache
:stop_httpd
call :prepare_env
echo  "%HOST_NAME%: stopping httpd ...\c"
%HTTPD_HOME%\bin\httpd.exe -f %BASE_BIN_DIR%\..\config\httpd\httpd.window.conf -w -n "Apache2.2" -k stop
echo  "success Oook!"

goto :eof
@rem 启动jetty
:start_jetty
call :prepare_env
set JETTY_CHECK_LOG="%LOG_DIR%\jetty_stdout.log"
@rem jetty server
if exist %JETTY_CHECK_LOG% (
    move /y %JETTY_CHECK_LOG% "%LOGS_SAVED%/jetty_stdout.log.%TIMESTAMP%"
)
@rem  start jetty
echo  "%HOST_NAME%: starting jetty ..."
call %BASE_BIN_DIR%\jettyctl.bat start

goto :eof
@rem 启动jetty
:stop_jetty
set BASE_BIN_DIR=%~dp0.
set HOST_NAME=%USERDNSDOMAIN%
echo  "%HOST_NAME%: stopping jetty ... "
call %BASE_BIN_DIR%\jettyctl.bat stop


