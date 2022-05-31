package ru.maksonic.rdcompose.core.store

import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface KeyStore {
    interface NavigationKey {
        val passedCategoryIdKey: String
        val passedCategoryNameKey: String
        val passedCategoryImgKey: String
        val passedPrivacyKey: String
        val privacyResult: String
        val termsOfUseResult: String
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