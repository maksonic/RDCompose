plugins {
    androidApp()
    kotlinAndroid()
    googlePlayServices()
    kapt()
    hilt()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(Module.DATA))
    implementation(project(Module.DOMAIN))
    implementation(project(Module.Shared.THEME))
    implementation(project(Module.Shared.UI_WIDGET))
    implementation(project(Module.Navigation.API))
    implementation(project(Module.Navigation.IMPL))

    implementation(project(Feature.ONBOARDING))
    implementation(project(Feature.USER_AUTH))
    implementation(project(Feature.PRIVACY))

    implementation(project(Screen.MAIN))
    implementation(project(Screen.HOME))
    implementation(project(Screen.CATEGORIES))
    implementation(project(Screen.PODCAST_LIST))
    implementation(project(Screen.COLLECTIONS))
    implementation(project(Screen.SETTINGS))
    implementation(project(Screen.USER_PROFILE))

    implementation(Lib.AndroidX.CORE_KTX)
    implementation(Lib.Accompanist.SYSTEM_UI)
    implementation(Lib.Compose.ACTIVITY)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Compose.NAVIGATION)
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.UI_PREVIEW)
    implementation(Lib.Lifecycle.RUNTIME_KTX)
    implementation(Lib.Firebase.FIRESTORE)
    implementation(Lib.Dagger.HILT)
    kapt(Lib.Dagger.COMPILER)

    testImplementation(Lib.Test.Junit.JUNIT)
    androidTestImplementation(Lib.Test.Junit.EXT)
    androidTestImplementation(Lib.Test.Espresso.CORE)
    androidTestImplementation(Lib.Test.Compose.UI)
    debugImplementation(Lib.Test.Compose.UI_TOOLING)
}