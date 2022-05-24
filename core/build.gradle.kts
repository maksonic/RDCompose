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
    implementation(Lib.AndroidX.DATASTORE)
    implementation(Lib.AndroidX.MATERIAL)
    implementation(Lib.Accompanist.SYSTEM_UI)
    implementation(Lib.Compose.ACTIVITY)
    implementation(Lib.AndroidX.CORE_KTX)
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Dagger.HILT)
    implementation(Lib.Dagger.VIEWMODEL)
    implementation(Lib.JetBrains.Serialization.JSON)
    kapt(Lib.Dagger.COMPILER)
}