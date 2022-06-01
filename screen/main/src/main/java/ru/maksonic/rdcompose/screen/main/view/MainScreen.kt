package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
    Column(modifier.systemBarsPadding()) {
        Box(modifier.weight(1f),
            contentAlignment = Alignment.TopCenter) {
            NavHost(
                navController,
                startDestination = HomeDestination.route,
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
            MainTopAppBar(model, sendMsg)
        }
        MainBottomNavBar(sendMsg, navController)
    }
}