@echo off
echo Building Engine JAR...

:: Set JAVA_HOME for this script
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

:: Create bin directory
if not exist bin mkdir bin

:: Compile
dir /s /B src\*.java > sources.txt
javac -d bin @sources.txt

if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed!
    del sources.txt
    exit /b 1
)

del sources.txt

:: Create JAR
jar cvf engine.jar -C bin .

if %errorlevel% neq 0 (
    echo [ERROR] JAR creation failed!
    exit /b 1
)

echo [SUCCESS] engine.jar created!
