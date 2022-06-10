package ru.maksonic.rdcompose.core.store

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface KeyStore {

    val appThemeKey:  Preferences.Key<String>
    val currentStoryKey:  Preferences.Key<Int>
    val fetchDataCategoryId: String

    interface NavigationKey {
        val passedCategoryIdKey: String
        val passedCategoryNameKey: String
        val passedCategoryImgKey: String
        val passedPrivacyKey: String
        val privacyResult: String
        val termsOfUseResult: String
    }

    class Base @Inject constructor(): KeyStore {
        override val appThemeKey = stringPreferencesKey("prefs_setting_theme")
        override val currentStoryKey = intPreferencesKey("prefs_current_story")
        override val fetchDataCategoryId = "id"
    }
    class NavigationPassedKey @Inject constructor() : NavigationKey {
        override val passedCategoryIdKey: String
            get() = "categoryId"
        override val passedCategoryNameKey: String
            get() = "categoryName"
        override val passedCategoryImgKey: String
            get() = "categoryImg"
        override val passedPrivacyKey: String
            get() = "privacyId"
        override val privacyResult: String
            get() = "privacyResult"
        override val termsOfUseResult: String
            get() = "termsOfUseResult"
    }
}