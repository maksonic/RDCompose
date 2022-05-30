package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
fun PodcastListScreenUi(modifier: Modifier = Modifier) {
    val viewModel: PodcastListViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    Scaffold(
        topBar = {
            TopAppBarNormal(
                backgroundColor = RDTheme.color.background,
                backPressed = { viewModel.backPressed() }
            )
        },
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        when {
            model.value.isLoading -> LoadingViewState()
            model.value.isError -> ErrorViewState(
                errorMessage = model.value.errorMsg,
                retryAction = { sendMsg(Msg.Ui.RetryFetchPodcasts) }
            )
            model.value.isSuccess -> {
                LazyColumn(modifier.padding(padding)) {
                    item {
                        CategoryHeader(model.value)
                    }
                    items(model.value.podcasts) { podcast ->
                        ItemPodcastList(podcast)
                    }
                }
            }
        }
    }
}