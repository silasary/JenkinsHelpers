#!/usr/bin/groovy
def restore() {
    if (isUnix())
    {
        sh 'nuget restore'
    }
    else
    {
        bat 'nuget restore'
    }
}