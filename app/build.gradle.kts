plugins {
    androidApp()
    kotlinAndroid()
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

    implementation(Lib.AndroidX.CORE_KTX)
    implementation(Lib.Compose.UI)
    implementation(Lib.Compose.MATERIAL)
    implementation(Lib.Compose.NAVIGATION)
    implementation(Lib.Compose.UI_PREVIEW)
    implementation(Lib.Lifecycle.RUNTIME_KTX)
    implementation(Lib.Compose.ACTIVITY)
    implementation(Lib.Dagger.HILT)
    kapt(Lib.Dagger.COMPILER)

    testImplementation(Lib.Test.Junit.JUNIT)
    androidTestImplementation(Lib.Test.Junit.EXT)
    androidTestImplementation(Lib.Test.Espresso.CORE)
    androidTestImplementation(Lib.Test.Compose.UI)
    debugImplementation(Lib.Test.Compose.UI_TOOLING)
}