package ru.maksonic.rdcompose.screen.podcast_list.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.clickAction
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 30.05.2022
 */
@Composable
internal fun ItemPodcastList(podcast: PodcastUi, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.itemNormalListHeight)
            .rippleClickable(rippleColor = RDTheme.color.primary) {  },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            imageModel = podcast.image,
            shimmerParams = ShimmerParams(
                baseColor = RDTheme.color.divider,
                highlightColor = RDTheme.color.surface,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            previewPlaceholder = R.drawable.podcast_image,
            error = painterResource(id = R.drawable.podcast_image),
            modifier = modifier
                .padding(start = RDTheme.padding.dp16, top = RDTheme.padding.dp8, bottom = RDTheme.padding.dp8)
                .aspectRatio(1f)

        )
        Text(
            text = podcast.name,
            style = RDTheme.typography.body,
            color = RDTheme.color.primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.weight(1f).padding(start = RDTheme.padding.dp8, end = RDTheme.padding.dp8)
        )

        IconActionButton(onClick = {}, modifier.padding(end = RDTheme.padding.dp8)) {
            Icon(
                painterResource(
                    id = R.drawable.ic_more_vertical
                ),
                tint = RDTheme.color.controlNormal,
                contentDescription = "",
            )
        }
    }
}