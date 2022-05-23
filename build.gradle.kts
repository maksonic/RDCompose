buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.ToolsGradle.init)
        classpath(Build.HiltGradle.init)
    }
}

plugins {
    kotlinGradlePlugin()
    kotlinSerializationPlugin()
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}