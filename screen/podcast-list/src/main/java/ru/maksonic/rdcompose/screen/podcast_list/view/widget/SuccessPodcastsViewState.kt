package ru.maksonic.rdcompose.screen.podcast_list.view.widget

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import ru.maksonic.rdcompose.screen.podcast_list.view.Message
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect
import ru.maksonic.rdcompose.shared.ui_widget.system.SwipeRefreshDefault

/**
 * @Author maksonic on 14.06.2022
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun SuccessPodcastsViewState(
    model: Model,
    sendMsg: Message,
    scope: CoroutineScope,
    playerSheet: BottomSheetScaffoldState,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {

    SwipeRefreshDefault(
        isRefreshing = model.baseModel.isRefreshing,
        onRefresh = { sendMsg(Msg.Ui.SwipeRefreshPodcasts) },
        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)) {

        OverscrollEffect {
            LazyColumn(
                modifier
                    .fillMaxWidth()
                    .systemBarsPadding(),
                state = lazyListState
            ) {
                item {
                    CategoryHeader(
                        model,
                        modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
                    )
                }
                items(model.podcasts) { podcast ->
                    ItemPodcastList(sendMsg, podcast, scope, playerSheet)
                }
            }
        }
    }
}