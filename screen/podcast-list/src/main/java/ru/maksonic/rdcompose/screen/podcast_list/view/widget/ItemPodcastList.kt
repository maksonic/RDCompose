package ru.maksonic.rdcompose.screen.podcast_list.view.widget

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.screen.podcast_list.view.Message
import ru.maksonic.rdcompose.screen.podcast_list.view.extractMediaSourceFromUri
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable
import ru.maksonic.rdcompose.shared.ui_widget.component.CoilShimmerImage

/**
 * @Author maksonic on 30.05.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ItemPodcastList(
    sendMsg: Message,
    podcast: PodcastUi,
    scope: CoroutineScope,
    playerSheet: BottomSheetScaffoldState,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .fillMaxWidth()
            .height(RDTheme.componentSize.itemNormalListHeight)
            .rippleClickable(rippleColor = RDTheme.color.primary) {
                sendMsg(Msg.Ui.OnPodcastClicked(scope, playerSheet))
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
            CoilShimmerImage(data = podcast.image, modifier = modifier.aspectRatio(1f))
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

        IconActionButton(onClick = {
        }, modifier.padding(end = RDTheme.padding.dp8)) {
            Icon(
                painterResource(id = R.drawable.ic_more_vertical),
                tint = RDTheme.color.controlNormal,
                contentDescription = "",
            )
        }
    }
}