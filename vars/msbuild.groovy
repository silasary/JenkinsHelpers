def call(body){
 if (isUnix())
    {
        sh 'nuget restore'
        sh 'xbuild /t:Build /p:Configuration=Release'
    }
    else
    {
      bat 'nuget restore'
      bat 'msbuild /t:Build /p:Configuration=Release'
    }
}