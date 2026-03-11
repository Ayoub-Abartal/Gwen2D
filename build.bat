@echo off
echo ================================
echo Building Gwen2D Framework
echo ================================

:: Set JAVA_HOME for this script
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

:: Read version from properties file
for /f "tokens=2 delims==" %%a in ('findstr "version=" version.properties') do set VERSION=%%a

echo Version: %VERSION%

:: Create bin directory
if not exist bin mkdir bin

:: Compile
echo Compiling sources...
dir /s /B src\*.java > sources.txt
javac -d bin @sources.txt

if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed!
    del sources.txt
    exit /b 1
)

del sources.txt

:: Create versioned JAR
echo Creating JAR...
jar cvf gwen2d-%VERSION%.jar -C bin .

if %errorlevel% neq 0 (
    echo [ERROR] JAR creation failed!
    exit /b 1
)

:: Copy to lib folder with generic name
copy gwen2d-%VERSION%.jar ..\lib\gwen2d-%VERSION%.jar

echo ================================
echo [SUCCESS] gwen2d-%VERSION%.jar created!
echo Copied to lib\gwen2d-%VERSION%.jar
echo ================================

pause
