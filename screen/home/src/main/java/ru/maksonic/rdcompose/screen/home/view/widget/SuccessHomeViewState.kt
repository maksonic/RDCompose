package ru.maksonic.rdcompose.screen.home.view.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.view.Message
import ru.maksonic.rdcompose.screen.home.view.widget.new_podcast.NewPodcastsViewPager
import ru.maksonic.rdcompose.screen.home.view.widget.stories.AudioStoryWidget
import ru.maksonic.rdcompose.shared.ui_widget.R.string
import ru.maksonic.rdcompose.shared.ui_widget.component.ScreenTitleDisplay
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect
import ru.maksonic.rdcompose.shared.ui_widget.system.SwipeRefreshDefault

/**
 * @Author maksonic on 14.06.2022
 */
@Composable
internal fun SuccessHomeViewState(model: Model, sendMsg: Message, modifier: Modifier = Modifier) {
    val newPodcasts = model.content.newPodcasts
    val recommendPodcasts = model.content.recommendPodcasts
    val topPodcasts = model.content.topPodcasts
    val randomPodcasts = model.content.randomPodcasts

    SwipeRefreshDefault(
        isRefreshing = model.baseModel.isRefreshing,
        onRefresh = { sendMsg(Msg.Ui.RefreshHomeContent) }) {

        OverscrollEffect {
            LazyColumn() {
                item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_home)) }
                item { AudioStoryWidget(model, sendMsg, modifier.fillParentMaxHeight(0.12f)) }
                item {
                    ItemHeader(
                        title = stringResource(string.txt_header_new_podcasts),
                        showActionBtn = false
                    )
                }
                item { NewPodcastsViewPager(newPodcasts) }
                item { ItemHeader(title = stringResource(string.txt_header_recommend_podcasts)) }
                item {
                    PodcastsRowItem(
                        recommendPodcasts,
                        onClick = {},
                        modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.2f),
                    )
                }
                item {
                    ItemHeader(title = stringResource(string.txt_header_top_podcasts))
                }
                item {
                    PodcastsRowItem(
                        topPodcasts,
                        onClick = {},
                        modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.2f),
                    )
                }
                item { ItemHeader(title = stringResource(string.txt_header_random_podcasts)) }

                item {
                    PodcastsRowItem(
                        randomPodcasts,
                        onClick = {},
                        modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.2f),
                    )
                }
            }
        }
    }
}