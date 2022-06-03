package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.ErrorViewState
import ru.maksonic.rdcompose.shared.ui_widget.LoadingViewState
import ru.maksonic.rdcompose.shared.ui_widget.topbar.TopAppBarNormal

/**
 * @Author maksonic on 26.05.2022
 */
typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PodcastListScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    PodcastListScreenUi(playerBottomSheetState)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PodcastListScreenUi(
    playerBottomSheetState: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)
    val viewModel: PodcastListViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        when {
            model.value.isLoading -> LoadingViewState()
            model.value.isError -> ErrorViewState(
                errorMessage = model.value.errorMsg,
                retryAction = { sendMsg(Msg.Ui.RetryFetchPodcasts) }
            )
            model.value.isSuccess -> SuccessPodcasts(
                model = model.value,
                sendMsg = sendMsg,
                playerSheet = playerBottomSheetState,
                backPressed = { viewModel.backPressed() },
                modifier = modifier.padding(padding)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SuccessPodcasts(
    model: Model,
    sendMsg: Message,
    playerSheet: BottomSheetScaffoldState,
    backPressed: () -> Unit,
    modifier: Modifier
) {
    val lazyListState = rememberLazyListState()
    val titleVisibility = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }
    val alphaBg = remember {
        derivedStateOf { if (lazyListState.firstVisibleItemIndex > 0) 1f else 0.7f }
    }

    Box(modifier.fillMaxSize()) {
        LazyColumn(
            modifier.fillMaxWidth(),
            state = lazyListState
        ) {
            item {
                CategoryHeader(
                    model,
                    modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
                )
            }

            items(model.podcasts) { podcast ->
                ItemPodcastList(sendMsg, podcast, playerSheet)
            }
        }

        TopAppBarNormal(
            title = model.categoryInfo.name,
            titleVisibilityState = titleVisibility.value,
            bgAlpha = alphaBg.value,
            backPressed = backPressed
        )
    }
}