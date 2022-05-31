package ru.maksonic.rdcompose.navigation.api.destination

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.maksonic.rdcompose.navigation.api.R

/**
 * @Author maksonic on 28.05.2022
 */
object CategoriesDestination : Route("categories") {

    object Categories : Route("categories.screen"), BottomNavRoute {
        @DrawableRes
        override val selectedIcon: Int = R.drawable.ic_category_filled

        @DrawableRes
        override val unselectedIcon: Int = R.drawable.ic_category_outlined

        @StringRes
        override val labelId: Int = R.string.scr_categories
    }

    object PodcastList : Route("categories.podcast/{categoryId}") {
        fun id(categoryId: String) = "/${categoryId}"
     //   fun name(categoryName: String) = "/${categoryName}"
    }
}