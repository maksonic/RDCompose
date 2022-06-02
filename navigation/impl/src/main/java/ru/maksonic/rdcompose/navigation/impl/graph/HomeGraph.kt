package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.store.KeyStore
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

    @OptIn(ExperimentalMaterialApi::class)
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        playerBottomSheetState: BottomSheetScaffoldState
        ) {
        navGraphBuilder.navigation(
            route = HomeDestination.route,
            startDestination = HomeDestination.Home.route
        ) {
            composable(HomeDestination.Home.route) {
                HomeScreen(playerBottomSheetState)
            }
        }
    }
}