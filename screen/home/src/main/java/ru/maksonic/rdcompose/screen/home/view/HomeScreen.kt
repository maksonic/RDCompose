package ru.maksonic.rdcompose.screen.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.maksonic.rdcompose.core.utils.PlayerBackPressed
import ru.maksonic.rdcompose.navigation.api.R
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.update.HomeViewModel
import ru.maksonic.rdcompose.screen.home.view.widget.ItemHeader
import ru.maksonic.rdcompose.screen.home.view.widget.news_podcast.NewsPodcastsViewPager
import ru.maksonic.rdcompose.screen.home.view.widget.stories.AudioStoryWidget
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R.*
import ru.maksonic.rdcompose.shared.ui_widget.ScreenTitleDisplay

/**
 * @Author maksonic on 25.05.2022
 */
internal typealias Message = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(playerBottomSheetState: BottomSheetScaffoldState) {
    val viewModel: HomeViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg

    HomeScreenUi(playerBottomSheetState, model.value, sendMsg)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenUi(
    playerBottomSheetState: BottomSheetScaffoldState,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {
    PlayerBackPressed(playerBottomSheetState)

    Scaffold(
        backgroundColor = RDTheme.color.background,
        modifier = modifier.padding(top = RDTheme.componentSize.smallTopBarHeight)
    ) { padding ->
        StoryDialog(model, sendMsg)

        LazyColumn(modifier.padding(padding)) {
            item { ScreenTitleDisplay(title = stringResource(id = R.string.scr_home)) }
            item { AudioStoryWidget(model, sendMsg) }
            item { ItemHeader(stringResource(string.txt_header_new_podcasts)) }
            item { NewsPodcastsViewPager(model) }
            item { ItemHeader(stringResource(string.txt_header_recommend_podcasts)) }

        }
    }
}