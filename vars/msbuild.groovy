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
      bat 'msbuild /t:Build /p:Configuration=RELEASE'
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
      bat 'msbuild /t:Build /p:Configuration=DEBUG'
    }
}