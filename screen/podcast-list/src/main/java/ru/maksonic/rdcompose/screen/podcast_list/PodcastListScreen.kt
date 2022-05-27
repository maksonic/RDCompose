package ru.maksonic.rdcompose.screen.podcast_list

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 26.05.2022
 */
@Composable
fun PodcastListScreen() {
    PodcastListScreenUi()
}

@Composable
fun PodcastListScreenUi(modifier: Modifier = Modifier) {
    Scaffold(backgroundColor = RDTheme.color.background) {
        Text("Podcast list")
    }
}