def call(String exe, String args){
    if (isUnix())
    {
        sh "mono ${exe} ${args}"
    }
    else
    {
        bat "${exe} ${args}"
    }
}