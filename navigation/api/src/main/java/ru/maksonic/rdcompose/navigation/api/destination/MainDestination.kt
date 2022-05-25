package ru.maksonic.rdcompose.navigation.api.destination

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.maksonic.rdcompose.navigation.api.R

/**
 * @Author maksonic on 25.05.2022
 */
object MainDestination : Route("main") {
    object Home : Route("main.home"), BottomNavRoute {
        @DrawableRes
        override val selectedIcon: Int = R.drawable.ic_rd_filled

        @DrawableRes
        override val unselectedIcon: Int = R.drawable.ic_rd_outlined

        @StringRes
        override val labelId: Int = R.string.scr_home
    }

    object Categories : Route("main.categories"), BottomNavRoute {
        @DrawableRes
        override val selectedIcon: Int = R.drawable.ic_category_filled

        @DrawableRes
        override val unselectedIcon: Int = R.drawable.ic_category_outlined

        @StringRes
        override val labelId: Int = R.string.scr_categories
    }

    object Collections : Route("main.collections"), BottomNavRoute {
        @DrawableRes
        override val selectedIcon: Int = R.drawable.ic_collections_filled

        @DrawableRes
        override val unselectedIcon: Int = R.drawable.ic_collections_outlined

        @StringRes
        override val labelId: Int = R.string.scr_collections
    }
}