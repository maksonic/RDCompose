package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.common.KeyStore
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.HomeDestination
import ru.maksonic.rdcompose.screen.home.HomeScreen
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
class HomeGraph @Inject constructor(
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder {

    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.navigation(
            route = HomeDestination.route,
            startDestination = HomeDestination.Home.route
        ) {
            composable(HomeDestination.Home.route) {
                HomeScreen()
            }
        }
    }
}