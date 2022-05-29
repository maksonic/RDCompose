package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import ru.maksonic.rdcompose.navigation.api.destination.CollectionsDestination
import ru.maksonic.rdcompose.navigation.api.destination.HomeDestination
import ru.maksonic.rdcompose.navigation.api.destination.Route
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
internal fun MainBottomNavBar(sendMsg: Message, navController: NavController) {
    val items = listOf(
        HomeDestination.Home,
        CategoriesDestination.Categories,
        CollectionsDestination.Collections,
    )

    NavigationBar(
        containerColor = RDTheme.color.surface,
        contentColor = RDTheme.color.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val currentDestination = navBackStackEntry?.destination
        mainTopBarBehavior(items, currentRoute, sendMsg)

        items.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val iconId = if (selected) screen.selectedIcon else screen.unselectedIcon
            val tint = if (selected) RDTheme.color.primary else RDTheme.color.secondary
            val label = stringResource(screen.labelId)

            NavigationBarItem(
                icon = {
                    Icon(painterResource(id = iconId), tint = tint, contentDescription = label)
                },
                label = { Text(label, color = tint, fontWeight = FontWeight.Bold) },
                alwaysShowLabel = true,
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = RDTheme.color.divider
                ),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(checkNotNull(navController.graph.findStartDestination().id)) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

private fun mainTopBarBehavior(list: List<Route>, currentRoute: String?, sendMsg: Message) {
    if (currentRoute == list[0].route ||
        currentRoute == list[1].route ||
        currentRoute == list[2].route
    ) {
        sendMsg(Msg.Internal.ShowTopBar)
    } else {
        sendMsg(Msg.Internal.HideTopBar)
    }
}