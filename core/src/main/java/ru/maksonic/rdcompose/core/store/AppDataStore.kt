package ru.maksonic.rdcompose.core.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

/**
 * @author maksonic on 27.05.2022
 */
interface AppDataStore {
    val Context.datastore: DataStore<Preferences>

    class ThemeSetting @Inject constructor() : AppDataStore {
        companion object {
            private const val DS_NAME = "datastore_settings"
        }

        override val Context.datastore by preferencesDataStore(name = DS_NAME)
    }
}