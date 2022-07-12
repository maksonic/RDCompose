package ru.maksonic.rdcompose.screen.main.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.feature.player.view.PlayerBottomSheet
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.HomeDestination
import ru.maksonic.rdcompose.screen.main.model.Model
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.screen.main.update.MainViewModel
import ru.maksonic.rdcompose.core.utils.currentFraction
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 23.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    homeGraphBuilder: GraphBuilder,
    categoriesGraphBuilder: GraphBuilder,
    collectionsGraphBuilder: GraphBuilder,
    playerBottomSheetState: BottomSheetScaffoldState,
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
        playerBottomSheetState,
        model.value,
        sendMsg
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreenUi(
    homeGraphBuilder: GraphBuilder,
    categoriesGraphBuilder: GraphBuilder,
    collectionsGraphBuilder: GraphBuilder,
    navController: NavHostController,
    playerBottomSheetState: BottomSheetScaffoldState,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    Scaffold(
        modifier.systemBarsPadding(),
        bottomBar = {
            val isHidden =
                animateFloatAsState(targetValue = playerBottomSheetState.currentFraction * 500)

            MainBottomNavBar(
                sendMsg,
                navController,
                modifier.graphicsLayer(
                    alpha = 1f - playerBottomSheetState.currentFraction,
                    translationY = isHidden.value
                )
            )
        },
        backgroundColor = RDTheme.color.background
    ) { padding ->
        val sheetShape = (24 * playerBottomSheetState.currentFraction).dp

        BottomSheetScaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = playerBottomSheetState,
            sheetShape = RoundedCornerShape(topStart = sheetShape, topEnd = sheetShape),
            sheetContent = {
                PlayerBottomSheet(playerBottomSheetState)
            },
            sheetPeekHeight = RDTheme.componentSize.playerSheetPeekHeight,
            sheetBackgroundColor = RDTheme.color.surface,
        ) {
            Column(modifier.padding(padding)) {
                Box(
                    modifier.weight(1f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    NavHost(
                        navController,
                        startDestination = HomeDestination.route,
                    ) {
                        homeGraphBuilder.buildNavGraph(
                            navGraphBuilder = this, navController, playerBottomSheetState
                        )
                        categoriesGraphBuilder.buildNavGraph(
                            navGraphBuilder = this, navController, playerBottomSheetState
                        )
                        collectionsGraphBuilder.buildNavGraph(
                            navGraphBuilder = this, navController, playerBottomSheetState
                        )
                    }
                    MainTopAppBar(model, sendMsg)
                }
            }
        }
    }
}

