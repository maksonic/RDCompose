package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.screen.podcast_list.update.PodcastListViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
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
    val state = viewModel.list.collectAsState()
    Scaffold(
        topBar = {
            TopAppBarNormal(
                title = "",
                backgroundColor = RDTheme.color.background,
                backPressed = { viewModel.backPressed() }
            )
        },
        backgroundColor = RDTheme.color.background,
        modifier = modifier.systemBarsPadding()
    ) { padding ->
        LazyColumn() {
            items(state.value) { podcast ->
                Column(modifier.wrapContentSize().padding(8.dp)) {
                    Text(podcast.id.toString())
                    Text(podcast.name) }
            }
        }
    }
}