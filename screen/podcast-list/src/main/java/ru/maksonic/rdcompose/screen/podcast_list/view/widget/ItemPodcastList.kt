package ru.maksonic.rdcompose.screen.podcast_list.view.widget

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.component.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 30.05.2022
 */
@Composable
internal fun ItemPodcastList(
    podcast: PodcastUi,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val musicUri = MediaItem.fromUri(Uri.parse(podcast.soundFile))
    val player = ExoPlayer.Builder(context).build()

    LaunchedEffect(podcast) {
        player.apply {
            val dataSourceFactory = DefaultDataSource.Factory(context)
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(musicUri)
            setMediaSource(source)
            playWhenReady = false
            prepare()
        }
    }

    Row(
        modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.itemNormalListHeight)
            .rippleClickable(rippleColor = RDTheme.color.primary) {
                if (player.isPlaying) {
                    player.pause()
                } else {
                    player.play()
                }
                //     sendMsg(Msg.Ui.OnPodcastClicked(scope, playerSheet))
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Card(
            modifier.padding(
                start = RDTheme.padding.dp16,
                top = RDTheme.padding.dp8,
                bottom = RDTheme.padding.dp8
            ),
            backgroundColor = RDTheme.color.surface,
            shape = RDTheme.shape.cornerSmall,
            elevation = RDTheme.elevation.elevationDisable
        ) {
            ImageWithShimmer(podcast.image, modifier.aspectRatio(1f))
        }

        Text(
            text = podcast.name,
            style = RDTheme.typography.body,
            color = RDTheme.color.primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .weight(1f)
                .padding(start = RDTheme.padding.dp8, end = RDTheme.padding.dp8)
        )

        IconActionButton(onClick = { player.pause() }, modifier.padding(end = RDTheme.padding.dp8)) {
            Icon(
                painterResource(id = R.drawable.ic_more_vertical),
                tint = RDTheme.color.controlNormal,
                contentDescription = "",
            )
        }
    }
}