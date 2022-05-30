package ru.maksonic.rdcompose.core.common

import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface KeyStore {
   interface NavigationKey {
       val passedCategoryIdKey: String
       val passedCategoryNameKey: String
       val passedCategoryImgKey: String
   }

    class NavigationPassedKey @Inject constructor() : NavigationKey {
        override val passedCategoryIdKey: String
            get() = "categoryId"
        override val passedCategoryNameKey: String
            get() = "categoryName"
        override val passedCategoryImgKey: String
            get() = "categoryImg"
    }
}