package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.MainDestination
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.screen.main.update.MainViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun MainScreen(mainGraphBuilder: GraphBuilder, mainNavigator: MainNavigator) {
    mainNavigator.navController = rememberNavController()
    val navController = mainNavigator.navController
    val viewModel: MainViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    MainScreenUi(mainGraphBuilder, navController, sendMsg)
}

@Composable
fun MainScreenUi(
    mainGraphBuilder: GraphBuilder,
    navController: NavHostController,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.systemBarsPadding(),
        topBar = { MainTopAppBar(sendMsg) },
        bottomBar = { MainBottomNavBar(navController) },
        backgroundColor = RDTheme.color.background,
    ) { padding ->
        NavHost(
            navController,
            startDestination = MainDestination.route,
            modifier.padding(padding)
        ) {
            mainGraphBuilder.buildNavGraph(
                navGraphBuilder = this,
                navController,
            )
        }
    }
}