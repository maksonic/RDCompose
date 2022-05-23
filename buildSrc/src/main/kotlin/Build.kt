/**
 * @Author maksonic on 23.05.2022
 */
object Build {

    object Type {
        private const val RELEASE = "release"
        private const val DEBUG = "debug"
        const val CURRENT = RELEASE
    }

    object ToolsGradle {
        private const val version = "7.2.0"
        const val init = "com.android.tools.build:gradle:$version"
    }

    object HiltGradle {
        private const val version = "2.42"
        const val init = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }


    object KotlinGradle {
        private const val version = Config.kotlinVersion
        const val init = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object KotlinSerialization {
        private const val version = "1.6.10"
        const val init = "org.jetbrains.kotlin:kotlin-serialization:$version"
    }
}