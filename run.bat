@echo off
echo Starting Chilki mA...

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo Error: Java not found!
    echo Please install Java 8 or higher version
    pause
    exit /b 1
)

REM Start the application
start http://localhost:8848
java -jar chilkima.jar --server.port=8848
pause 