@echo off
echo Starting Chilki mA...
echo.

REM 检查 Maven 是否存在
where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Using installed Maven...
    set MVN_CMD=mvn
) else (
    echo Maven not found in PATH, trying to use mvnw...
    if exist mvnw.cmd (
        set MVN_CMD=mvnw.cmd
    ) else (
        echo Error: Neither Maven nor mvnw.cmd found!
        echo Please install Maven or run 'mvn wrapper:wrapper' to generate mvnw.cmd
        pause
        exit /b 1
    )
)

REM 等待3秒后自动打开浏览器
start /b cmd /c "timeout /t 3 /nobreak >nul && start http://localhost:5000"

echo Starting Spring Boot application...
echo The application will be available at http://localhost:5000
echo.
echo Press Ctrl+C to stop the application
echo.

%MVN_CMD% spring-boot:run

pause 