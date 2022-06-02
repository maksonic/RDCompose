import org.gradle.api.JavaVersion

/**
 * @Author maksonic on 23.05.2022
 */
object Config {
    const val composeVersion = "1.2.0-beta03"
    const val hiltPluginVersion = "2.42"
    const val kotlinVersion = "1.6.21"
    const val kspVersion = "1.6.20-1.0.5"
    const val serializationVersion = "1.6.10"

    const val appId = "ru.maksonic.rdcompose"
    const val compileSdk = 32
    const val targetSdk = 32
    const val minSdk = 23
    const val versionName = "1.0"
    const val versionCode = 1
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"

    val javaVersion = JavaVersion.VERSION_11
    const val jvmTarget = "11"
}