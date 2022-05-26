package ru.maksonic.rdcompose.core.common

import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface KeyStore {
   interface NavigationKey {
       val passedCategoryIdKey: String
   }

    class NavigationPassedKey @Inject constructor() : NavigationKey {
        override val passedCategoryIdKey: String
            get() = "categoryId"
    }
}