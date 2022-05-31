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
    implementation(project(Module.Navigation.API))
    implementation(project(Feature.ONBOARDING))
    implementation(project(Feature.PRIVACY))
    implementation(project(Feature.USER_AUTH))
    implementation(project(Screen.MAIN))
    implementation(project(Screen.HOME))
    implementation(project(Screen.CATEGORIES))
    implementation(project(Screen.PODCAST_LIST))
    implementation(project(Screen.COLLECTIONS))
    implementation(project(Screen.SETTINGS))
    implementation(project(Screen.USER_PROFILE))
    implementation(Lib.Compose.NAVIGATION)
    implementation(Lib.Compose.UI)
    implementation(Lib.Dagger.HILT)
    kapt(Lib.Dagger.COMPILER)
}