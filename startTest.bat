@echo off
set "str2=.html"

set "str=%time:~0,5%"

for /f "tokens=1,2 delims=/:" %%a in ("%str%") do set whour=%%a & set wmin=%%b
set whour=%whour: =%
set wmin=%wmin: =%

REM set "filename=%str1%%date:~10,4%%date:~4,2%%date:~7,2%%whour%%wmin%%str2%"
set "filename=%date:~10,4%%date:~4,2%%date:~7,2%%whour%%wmin%%str2%"

REM Browser Types
REM "*iehta" 
REM "*chrome" 

cd C:\Users\stephb\Documents\GitHub\AngloInfoTesting
ant mail

