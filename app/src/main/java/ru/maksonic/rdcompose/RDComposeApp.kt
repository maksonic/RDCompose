package ru.maksonic.rdcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author maksonic on 23.05.2022
 */
@HiltAndroidApp
class RDComposeApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}