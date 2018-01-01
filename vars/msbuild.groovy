def call()
{
    if (isUnix())
    {
        sh 'nuget restore'
        sh 'xbuild /t:Build /p:Configuration=RELEASE'
    }
    else
    {
      bat 'nuget restore'
      bat '''set PATH="%ProgramFiles(x86)%\\Microsoft Visual Studio\\Installer\\;%PATH%"
set vswhere="%ProgramFiles(x86)%\\Microsoft Visual Studio\\Installer\\vswhere.exe"
if not exist "%vswhere%" (
    curl -O https://github.com/Microsoft/vswhere/releases/download/1.0.71/vswhere.exe
    set vswhere="vswhere"
)
for /f "usebackq tokens=1* delims=: " %%i in (`vswhere -latest -products * -requires Microsoft.Component.MSBuild -property installationPath`) do (
  if /i "%%i"=="installationPath" set InstallDir=%%j
)

set msbuild="msbuild"
if exist "%InstallDir%\\MSBuild\\15.0\\Bin\\MSBuild.exe" (
  set msbuild="%InstallDir%\\MSBuild\\15.0\\Bin\\MSBuild.exe"
)
%msbuild% /t:Build /p:Configuration=RELEASE'''
    }
}

def debug()
{
    if (isUnix())
    {
        sh 'nuget restore'
        sh 'xbuild /t:Build /p:Configuration=DEBUG'
    }
    else
    {
      bat 'nuget restore'
      bat '''set PATH="%PATH%;%ProgramFiles(x86)%\\Microsoft Visual Studio\\Installer\\"
if not exist "%ProgramFiles(x86)%\\Microsoft Visual Studio\\Installer\\vswhere.exe" (
    curl -O https://github.com/Microsoft/vswhere/releases/download/1.0.71/vswhere.exe
)
for /f "usebackq tokens=1* delims=: " %%i in (`vswhere -latest -products * -requires Microsoft.Component.MSBuild -property installationPath`) do (
  if /i "%%i"=="installationPath" set InstallDir=%%j
)

set msbuild="msbuild"
if exist "%InstallDir%\\MSBuild\\15.0\\Bin\\MSBuild.exe" (
  set msbuild="%InstallDir%\\MSBuild\\15.0\\Bin\\MSBuild.exe"
)
%msbuild% /t:Build /p:Configuration=DEBUG'''
    }
}