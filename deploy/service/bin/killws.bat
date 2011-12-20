@echo off
if "%OS%"=="Windows_NT" @setlocal

call functions.bat stop_jetty
call functions.bat stop_httpd

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal