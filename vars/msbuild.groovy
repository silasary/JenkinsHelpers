def call()
{
    if (isUnix())
    {
        sh 'nuget restore'
        sh 'msbuild /t:Build /p:Configuration=RELEASE'
    }
    else
    {
        bat 'nuget restore'
        msbuildcmd = libraryResource 'msbuild.cmd'
        writeFile file: 'msbuild.cmd', text: msbuildcmd
        bat 'msbuild /t:Build /p:Configuration=RELEASE'
    }
}

def debug()
{
    if (isUnix())
    {
        sh 'nuget restore'
        sh 'msbuild /t:Build /p:Configuration=DEBUG'
    }
    else
    {
        bat 'nuget restore'
        msbuildcmd = libraryResource 'msbuild.cmd'
        writeFile file: 'msbuild.cmd', text: msbuildcmd
        bat 'msbuild /t:Build /p:Configuration=DEBUG'''
    }
}