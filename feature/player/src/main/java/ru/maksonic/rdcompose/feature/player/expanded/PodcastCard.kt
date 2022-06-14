package ru.maksonic.rdcompose.feature.player.expanded

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.component.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.R.*

/**
 * @Author maksonic on 02.06.2022
 */
@Composable
fun PodcastCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(
                start = RDTheme.padding.dp32,
                end = RDTheme.padding.dp32,
                top = RDTheme.padding.dp16,
                bottom = RDTheme.padding.dp16
            )
            .aspectRatio(1f),
        backgroundColor = RDTheme.color.surface,
        shape = RDTheme.shape.cornerBig,
        elevation = RDTheme.elevation.dp8,
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            ImageWithShimmer(drawable.podcast_image)
        }
    }
}