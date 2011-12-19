@echo off
if "%OS%"=="Windows_NT" @setlocal

call functions.bat start_httpd
call functions.bat start_jetty


@rem 退出
:end
if "%OS%"=="Windows_NT" @endlocal