buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.ToolsGradle.init)
        classpath(Build.GoogleService.init)
        classpath(Build.HiltGradle.init)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}


task<Delete>("clean") {
    delete(rootProject.buildDir)
}