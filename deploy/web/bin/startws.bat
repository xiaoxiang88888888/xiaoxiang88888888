@echo off
if "%OS%"=="Windows_NT" @setlocal

call functions.bat start_jetty
call functions.bat start_httpd

@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal