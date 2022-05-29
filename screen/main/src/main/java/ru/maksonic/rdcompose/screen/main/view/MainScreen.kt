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
import ru.maksonic.rdcompose.navigation.api.destination.HomeDestination
import ru.maksonic.rdcompose.screen.main.model.Model
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.screen.main.update.MainViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun MainScreen(
    homeGraphBuilder: GraphBuilder,
    categoriesGraphBuilder: GraphBuilder,
    collectionsGraphBuilder: GraphBuilder,
) {

    val viewModel: MainViewModel = hiltViewModel()
    viewModel.mainNavigator.navController = rememberNavController()
    val navController = viewModel.mainNavigator.navController
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    MainScreenUi(
        homeGraphBuilder,
        categoriesGraphBuilder,
        collectionsGraphBuilder,
        navController,
        model.value,
        sendMsg
    )
}

@Composable
fun MainScreenUi(
    homeGraphBuilder: GraphBuilder,
    categoriesGraphBuilder: GraphBuilder,
    collectionsGraphBuilder: GraphBuilder,
    navController: NavHostController,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier.systemBarsPadding(),
        topBar = { MainTopAppBar(model, sendMsg) },
        bottomBar = { MainBottomNavBar(sendMsg, navController) },
        backgroundColor = RDTheme.color.background,
    ) { padding ->
        NavHost(
            navController,
            startDestination = HomeDestination.route,
            modifier.padding(padding)
        ) {
            homeGraphBuilder.buildNavGraph(
                navGraphBuilder = this,
                navController,
            )
            categoriesGraphBuilder.buildNavGraph(
                navGraphBuilder = this,
                navController,
            )
            collectionsGraphBuilder.buildNavGraph(
                navGraphBuilder = this,
                navController,
            )
        }
    }
}