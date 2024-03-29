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
    implementation(project(Module.Shared.THEME))
    implementation(Lib.Accompanist.PAGER)
    implementation(Lib.Accompanist.PLACEHOLDER)
    implementation(Lib.Coil.COMPOSE)
    implementation(Lib.Accompanist.SWIPE_REFRESH)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Compose.UI)
    implementation(Lib.Dagger.HILT)
    kapt(Lib.Dagger.COMPILER)
}