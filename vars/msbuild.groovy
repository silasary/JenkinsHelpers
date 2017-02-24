def call(String target = "RELEASE"){
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