package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
@Composable
fun PodcastListScreen() {
    PodcastListScreenUi()
}

@Composable
private fun PodcastListScreenUi(modifier: Modifier = Modifier) {
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
                backPressed = { viewModel.backPressed() },
                modifier = modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun SuccessPodcasts(model: Model, backPressed: () -> Unit, modifier: Modifier) {
    val lazyListState = rememberLazyListState()
    val titleVisibility = lazyListState.firstVisibleItemIndex > 0
    val alphaBg = if (lazyListState.firstVisibleItemIndex > 0) 1f else 0.7f

    Box(modifier.fillMaxSize()) {
        LazyColumn(
            modifier
                .fillMaxWidth(),
            state = lazyListState
        ) {
            item {
                CategoryHeader(
                    model,
                    modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
                )
            }

            items(model.podcasts) { podcast ->
                ItemPodcastList(podcast)
            }
        }

        TopAppBarNormal(
            title = model.categoryInfo.name,
            titleVisibilityState = titleVisibility,
            bgAlpha = alphaBg,
            backPressed = backPressed
        )
    }
}