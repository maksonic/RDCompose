package ru.maksonic.rdcompose.screen.categories.view

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
import ru.maksonic.rdcompose.screen.categories.model.Model
import ru.maksonic.rdcompose.screen.categories.model.Msg
import ru.maksonic.rdcompose.screen.categories.update.CategoriesViewModel
import ru.maksonic.rdcompose.screen.categories.view.widget.SuccessCategoriesViewState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.LoadingViewState

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    CategoriesScreenUi(model.value, sendMsg, playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CategoriesScreenUi(
    model: Model,
    sendMsg: Message,
    playerBottomSheetState: BottomSheetScaffoldState,
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
        when {
            model.baseModel.isLoading -> {
                LoadingViewState(modifier.padding(top = RDTheme.componentSize.smallTopBarHeight))
            }
            model.baseModel.isSuccess -> SuccessCategoriesViewState(model, sendMsg)
            model.baseModel.isError -> {
                ErrorViewState(
                    modifier.padding(padding),
                    retryAction = { sendMsg(Msg.Ui.FetchCategories) },
                    errorMessage = model.baseModel.errorMsg,
                )
            }
        }
    }
}

