@echo off
setlocal ENABLEDELAYEDEXPANSION

set PWD=%~dp0
 
FOR /R "%PWD%\lib" %%G IN (*.jar) DO set CP=!CP!;%%G

java -Xmx512m -cp "%CP%" karstenroethig.maven.example.App

pause
