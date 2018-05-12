if exist "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\MSBuild\15.0\Bin\MSBuild.exe" (
    "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\MSBuild\15.0\Bin\MSBuild.exe" %*
    exit /b !ERRORLEVEL!
)

@if not defined _echo echo off
setlocal enabledelayedexpansion

@REM Determine if MSBuild is already in the PATH
for /f "usebackq delims=" %%I in (`where msbuild.exe 2^>nul`) do (
    "%%I" %*
    exit /b !ERRORLEVEL!
)

set vswhere="%ProgramFiles(x86)%\\Microsoft Visual Studio\\Installer\\vswhere.exe"
if not exist %vswhere% (
    curl -O https://github.com/Microsoft/vswhere/releases/download/1.0.71/vswhere.exe
    set vswhere="vswhere"
)

@REM Find the latest MSBuild that supports our projects
for /f "usebackq delims=" %%I in (`%vswhere% -version "[15.0,)" -latest -prerelease -products * -requires Microsoft.Component.MSBuild Microsoft.VisualStudio.Component.Roslyn.Compiler Microsoft.VisualStudio.Component.VC.140 -property InstallationPath`) do (
    for /f "usebackq delims=" %%J in (`where /r "%%I\MSBuild" msbuild.exe 2^>nul ^| sort /r`) do (
        "%%J" %*
        exit /b !ERRORLEVEL!
    )
)

echo Could not find msbuild.exe 1>&2
exit /b 2
