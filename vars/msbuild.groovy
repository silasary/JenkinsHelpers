def call(body){
 if (isUnix())
    {
        sh 'nuget restore'
        sh 'xbuild'
    }
    else
    {
      bat 'nuget restore'
      bat 'msbuild'
    }
}