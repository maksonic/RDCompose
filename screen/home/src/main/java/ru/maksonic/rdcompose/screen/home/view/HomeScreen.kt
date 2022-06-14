package ru.maksonic.rdcompose.screen.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.update.HomeViewModel
import ru.maksonic.rdcompose.screen.home.view.widget.SuccessHomeViewState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.LoadingViewState

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: HomeViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    HomeScreenUi(playerBottomSheetState, model.value, sendMsg)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenUi(
    playerBottomSheetState: BottomSheetScaffoldState,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)


    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier
            .systemBarsPadding()
            .padding(
                top = RDTheme.componentSize.smallTopBarHeight,
                bottom = RDTheme.componentSize.playerCollapsedHeight
            )
    ) { padding ->
        StoryDialog(model, sendMsg)

        when {
            model.baseModel.isLoading -> {
                LoadingViewState(modifier.padding(top = RDTheme.componentSize.smallTopBarHeight))
            }
            model.baseModel.isError -> {
                ErrorViewState(
                    modifier.padding(top = RDTheme.componentSize.smallTopBarHeight),
                    retryAction = { sendMsg(Msg.Ui.FetchAllData) },
                    errorMessage = model.baseModel.errorMsg
                )
            }
            model.baseModel.isSuccess -> SuccessHomeViewState(model, sendMsg)
        }
    }
}