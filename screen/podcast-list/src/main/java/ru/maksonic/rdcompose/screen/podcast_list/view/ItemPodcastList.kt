package ru.maksonic.rdcompose.screen.podcast_list.view

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
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