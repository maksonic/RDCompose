package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.navigation.api.destination.MainDestination
import ru.maksonic.rdcompose.shared.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
@Composable
fun MainScreen(mainGraphBuilder: GraphBuilder, mainNavigator: MainNavigator) {
    mainNavigator.navController = rememberNavController()
    val navController = mainNavigator.navController

    MainScreenUi(mainGraphBuilder, navController)
}

@Composable
fun MainScreenUi(
    mainGraphBuilder: GraphBuilder,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.systemBarsPadding(),
        topBar = { MainTopAppBar(showSettings = {}) },
        bottomBar = { MainBottomNavBar(navController) },
        backgroundColor = RDTheme.color.background,
    ) { padding ->
        NavHost(
            navController,
            startDestination = MainDestination.route,
        ) {
            mainGraphBuilder.buildNavGraph(
                navGraphBuilder = this,
                navController
            )
        }
    }
}