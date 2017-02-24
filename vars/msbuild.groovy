def call(String target = "")
{
    if (target == "")
    {
        target = "RELEASE"
    }
    if (isUnix())
    {
        sh 'nuget restore'
        sh 'xbuild /t:Build /p:Configuration=${target}'
    }
    else
    {
      bat 'nuget restore'
      bat 'msbuild /t:Build /p:Configuration=${target}'
    }
}