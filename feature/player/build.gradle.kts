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
    implementation(project(Module.Shared.THEME))
    implementation(project(Module.Shared.UI_MODEL))
    implementation(project(Module.Shared.UI_WIDGET))
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Dagger.HILT)
    implementation(Lib.Dagger.VIEWMODEL)
    implementation(Lib.ExoPlayer.EXOPLAYER)
    implementation(Lib.ExoPlayer.SESSION)
    kapt(Lib.Dagger.COMPILER)
}