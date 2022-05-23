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
}