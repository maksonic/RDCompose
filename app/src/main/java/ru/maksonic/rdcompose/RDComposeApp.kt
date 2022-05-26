package ru.maksonic.rdcompose

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author maksonic on 23.05.2022
 */
@HiltAndroidApp
class RDComposeApp: Application() {
    override fun onCreate() {
      //  FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}