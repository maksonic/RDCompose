package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.screen.podcast_list.update.PodcastListViewModel
import ru.maksonic.rdcompose.screen.podcast_list.view.widget.SuccessPodcastsViewState
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.viewstate.LoadingViewState

/**
 * @Author maksonic on 26.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastListScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: PodcastListViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    PodcastListScreenUi(viewModel, model.value, sendMsg, playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PodcastListScreenUi(
    viewModel: PodcastListViewModel,
    model: Model,
    sendMsg: Message,
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    val lazyListState = rememberLazyListState()
    val titleVisibility = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val alphaBg = remember {
        derivedStateOf { if (lazyListState.firstVisibleItemIndex > 0) 1f else 0.7f }
    }

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding(),
    ) { padding ->

        Box(
            modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(bottom = RDTheme.componentSize.playerCollapsedHeight)
        ) {
            when {
                model.baseModel.isLoading -> {
                    LoadingViewState(
                        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
                    )
                }
                model.baseModel.isError -> {
                    ErrorViewState(
                        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight),
                        retryAction = { sendMsg(Msg.Ui.RetryFetchPodcasts) },
                        errorMessage = model.baseModel.errorMsg
                    )
                }
                model.baseModel.isSuccess -> {
                    SuccessPodcastsViewState(
                        model = model,
                        sendMsg = sendMsg,
                        playerSheet = playerBottomSheetState,
                        lazyListState = lazyListState,
                    )
                }
            }

            TopAppBarNormal(
                title = model.categoryInfo.name,
                titleVisibilityState = titleVisibility.value,
                bgAlpha = alphaBg.value,
                backPressed = { viewModel.backPressed() }
            )
        }
    }
}

