@echo off

set BAT_DIR=%~dp0%

REM java.exe have to be found in %PATH%
set JAVA_BIN=java.exe

"%JAVA_BIN%" -Xrs -Xmx256m ^
	-cp %BAT_DIR%\lib\* ^
	karstenroethig.maven.example.App
