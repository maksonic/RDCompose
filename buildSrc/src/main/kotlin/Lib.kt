/**
 * @Author maksonic on 23.05.2022
 */
object Lib {
    private const val composeVersion = Config.composeVersion

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
        const val DATASTORE = "androidx.datastore:datastore-preferences:1.0.0"
        const val MATERIAL = "com.google.android.material:material:1.5.0"
    }

    object Accompanist {
        private const val version = "0.24.9-beta"
        const val PAGER = "com.google.accompanist:accompanist-pager:$version"
        const val PAGER_INDICATORS = "com.google.accompanist:accompanist-pager-indicators:$version"
        const val SYSTEM_UI = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val SWIPE_REFRESH = "com.google.accompanist:accompanist-swiperefresh:$version"
    }

    object Compose {
        const val ACTIVITY = "androidx.activity:activity-compose:1.4.0"
        const val COMPILER = "androidx.compose.compiler:compiler:$composeVersion"
        const val MATERIAL = "androidx.compose.material:material:$composeVersion"
        const val NAVIGATION = "androidx.navigation:navigation-compose:2.5.0-alpha02"
        const val UI = "androidx.compose.ui:ui:$composeVersion"
        const val UI_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    }

    object Landscapist {
        private const val version = "1.5.2"
        const val GLIDE = "com.github.skydoves:landscapist-glide:$version"
    }

    object Dagger {
        private const val version = "2.42"
        const val HILT = "com.google.dagger:hilt-android:$version"
        const val COMPILER = "com.google.dagger:hilt-compiler:$version"
        const val VIEWMODEL = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Firebase {

        const val AUTH_KTX = "com.google.firebase:firebase-auth-ktx:21.0.4"
        const val FIRESTORE = "com.google.firebase:firebase-firestore-ktx:24.1.2"
        const val GMS_PLAY_SERVICE = "com.google.android.gms:play-services-tasks:18.0.1"
       /*"
        const val gmsTask = "com.google.android.gms:play-services-tasks:17.2.1"*/
    }

    object JetBrains {

        object Coroutines {
            private const val version = "1.6.1"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val PLAY_SERVICE = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
        }

        object Serialization {
            private const val version = "1.3.2"
            const val JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }
    }

    object Lifecycle {
        const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha02"
    }

    object Room {
        private const val version = "2.4.2"
        const val COMPILER = "androidx.room:room-compiler:$version"
        const val KTX = "androidx.room:room-ktx:$version"
        const val RUNTIME = "androidx.room:room-runtime:$version"

    }

    object Test {
        object Compose {
            const val UI = "androidx.compose.ui:ui-test-junit4:$composeVersion"
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$composeVersion"
        }

        object Espresso {
            private const val version = "3.4.0"
            const val CORE = "androidx.test.espresso:espresso-core:$version"
        }

        object Junit {
            const val JUNIT = "junit:junit:4.13"
            const val EXT = "androidx.test.ext:junit:1.1.3"
        }
    }

}