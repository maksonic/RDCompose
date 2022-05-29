package ru.maksonic.rdcompose.navigation.api

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import ru.maksonic.rdcompose.navigation.api.destination.CollectionsDestination
import ru.maksonic.rdcompose.navigation.api.destination.HomeDestination
import ru.maksonic.rdcompose.navigation.api.destination.Route

/**
 * @Author maksonic on 29.05.2022
 */
@Stable
@Composable
fun NavController.currentScreenAsState(): State<Route> {
    val selectedItem = remember { mutableStateOf<Route>(HomeDestination.Home) }
    val bottomItems = mapOf<Route, String>(
        HomeDestination.Home to HomeDestination.Home.route,
        CategoriesDestination.Categories to CategoriesDestination.Categories.route,
        CollectionsDestination.Collections to CollectionsDestination.Collections.route
    )
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->

            bottomItems.forEach { bottomItem ->
                destination.applyCurrent(selectedItem, bottomItem.key, bottomItem.value)
            }
        }

        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

private fun NavDestination.applyCurrent(
    selectedItem: MutableState<Route>, route: Route, screen: String
) {
    when {
        this.hierarchy.any { it.route == screen } -> selectedItem.value = route
    }
}

