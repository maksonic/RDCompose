package ru.maksonic.rdcompose.screen.podcast_list

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.button.clickAction

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
    Scaffold(backgroundColor = RDTheme.color.background) {
        Text(
            "Podcast list",
            color = RDTheme.color.primaryText,
            modifier = modifier.clickAction {  })
    }
}