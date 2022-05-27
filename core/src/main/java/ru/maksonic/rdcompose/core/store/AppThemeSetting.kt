package ru.maksonic.rdcompose.core.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
enum class AppTheme(val title: String) {
    SYSTEM("Системная тема"),
    LIGHT("Светлая тема"),
    DARK( "Тёмная тема"),
    HIGH_CONTRAST("Высококонтрастная тема")
}

interface AppThemeSetting {
    var currentTheme: MutableStateFlow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
    suspend fun readTheme(): MutableStateFlow<AppTheme>

    class Base @Inject constructor(
        private val context: Context,
        private val dataStore: AppDataStore
    ) : AppThemeSetting {
        companion object {
            private val KEY_STORE_THEME = stringPreferencesKey("prefs_setting_theme")
        }

        override var currentTheme: MutableStateFlow<AppTheme> = MutableStateFlow(AppTheme.SYSTEM)

        override suspend fun setTheme(theme: AppTheme) {
            dataStore.apply {
                context.datastore.edit { preferences ->
                    preferences[KEY_STORE_THEME] = theme.name
                }
            }
        }

        override suspend fun readTheme(): MutableStateFlow<AppTheme> {
            dataStore.apply {
                context.datastore.data.map { preferences ->
                    preferences[KEY_STORE_THEME] ?: AppTheme.SYSTEM.name
                }.collect {
                    currentTheme.value = AppTheme.valueOf(it)
                }
                return currentTheme
            }
        }
    }
}