package ru.maksonic.rdcompose.screen.home.view.widget

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import ru.maksonic.rdcompose.shared.theme.paddings
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable
import ru.maksonic.rdcompose.shared.ui_widget.component.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect

/**
 * @Author maksonic on 12.06.2022
 */
@Composable
internal fun PodcastsRowItem(
    podcasts: List<PodcastUi>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    OverscrollEffect {
        LazyRow(
            modifier
                .fillMaxWidth()
                .padding(start = RDTheme.padding.dp8, end = RDTheme.padding.dp8)
        ) {
            items(podcasts) { podcast -> Item(podcast, onClick) }
        }
    }
}

@Composable
private fun Item(podcast: PodcastUi, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Box(
        modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .padding(RDTheme.padding.dp8)
            .clip(RDTheme.shape.cornerSmall)
            .rippleClickable {
                Toast
                    .makeText(ctx, podcast.name, Toast.LENGTH_SHORT)
                    .show()
            }) {
        ImageWithShimmer(podcast.image)
    }
}