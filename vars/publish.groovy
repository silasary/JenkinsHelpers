def call(String args){
     if (!fileExists('clickmono.exe')) {
         sh('curl http://ci.katelyngigante.com/job/silasary/job/clickmono/job/master/lastSuccessfulBuild/artifact/ClickMac/bin/Release/ClickMac.exe -o clickmono.exe')
     }
     mono("clickmono.exe", "--packager ${args}")
}

@noCPS
def setDeploymentUrl(string baseUrl){
    manifests = findFiles(glob: '*.application')
    manifests.each {
        publish("--update ${it} --deploymentUrl ${baseurl}/${it} --generateBootstrap setup.exe")
    }
}