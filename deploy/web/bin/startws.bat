@echo off
if "%OS%"=="Windows_NT" @setlocal

@rem call functions.bat start_httpd

call functions.bat start_nginx

call functions.bat start_jetty

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal