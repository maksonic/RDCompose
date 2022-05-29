package ru.maksonic.rdcompose.navigation.api.destination

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.maksonic.rdcompose.navigation.api.R

/**
 * @Author maksonic on 28.05.2022
 */
object CollectionsDestination : Route("collections") {

    object Collections : Route("collections.screen"), BottomNavRoute {
        @DrawableRes
        override val selectedIcon: Int = R.drawable.ic_collections_filled

        @DrawableRes
        override val unselectedIcon: Int = R.drawable.ic_collections_outlined

        @StringRes
        override val labelId: Int = R.string.scr_collections
    }
}