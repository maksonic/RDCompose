plugins {
    androidLibrary()
    kotlinAndroid()
    hilt()
    kapt()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        testInstrumentationRunner = Config.androidJunitRunner
    }

    buildTypes {
        getByName(Build.Type.CURRENT) {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeVersion
    }
}

dependencies {
    implementation(project(Module.CORE))
    implementation(project(Module.DOMAIN))
    implementation(project(Module.Navigation.API))
    implementation(project(Module.Shared.THEME))
    implementation(project(Module.Shared.UI_WIDGET))
    implementation(project(Feature.USER_AUTH))
    implementation(Lib.Accompanist.PAGER)
    implementation(Lib.Accompanist.PAGER_INDICATORS)
    implementation(Lib.Accompanist.SYSTEM_UI)
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Dagger.HILT)
    implementation(Lib.Dagger.VIEWMODEL)
    kapt(Lib.Dagger.COMPILER)
}