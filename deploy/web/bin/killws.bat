@echo off
if "%OS%"=="Windows_NT" @setlocal

call functions.bat stop_jetty

@rem call functions.bat stop_httpd

call functions.bat stop_nginx

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal