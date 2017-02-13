def call(String exe, String args){
     if (!fileExists('clickmono.exe')) {
         sh('curl http://ci.katelyngigante.com/job/silasary/job/clickmono/job/master/lastSuccessfulBuild/artifact/ClickMac/bin/Release/ClickMac.exe -o clickmono.exe')
     }
     mono('clickmono.exe', '--packager ${exe} ${args}')
}