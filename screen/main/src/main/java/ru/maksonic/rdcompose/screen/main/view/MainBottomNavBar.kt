package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.maksonic.rdcompose.navigation.api.destination.MainDestination
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
internal fun MainBottomNavBar(navController: NavController) {
    val items = listOf(
        MainDestination.Home,
        MainDestination.Categories,
        MainDestination.Collections,
    )

    BottomAppBar(
        backgroundColor = RDTheme.color.surface
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MainDestination.Home.route

        for (screen in items) {
            val iconId =
                if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon
            val tint =
                if (currentRoute == screen.route) RDTheme.color.primary else RDTheme.color.secondary
            val label = stringResource(screen.labelId)
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(id = iconId), tint = tint, contentDescription = label)
                },
                label = { Text(label, color = tint) },
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(checkNotNull(navController.graph.startDestinationRoute)) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}