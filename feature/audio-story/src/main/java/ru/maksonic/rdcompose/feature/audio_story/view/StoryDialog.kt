package ru.maksonic.rdcompose.feature.audio_story.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.maksonic.rdcompose.feature.audio_story.model.Model
import ru.maksonic.rdcompose.feature.audio_story.model.Msg
import ru.maksonic.rdcompose.feature.audio_story.update.StoriesViewModel
import ru.maksonic.rdcompose.feature.audio_story.view.widget.ItemStoryPager
import ru.maksonic.rdcompose.feature.audio_story.view.widget.StoryBottomBar
import ru.maksonic.rdcompose.feature.audio_story.view.widget.StoryTopBar
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect

/**
 * @Author maksonic on 07.06.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun StoryDialog(isShowedDialog: Boolean, closeStoryDialog: () -> Unit) {
    val viewModel: StoriesViewModel = hiltViewModel()
    val model = viewModel.featureModel.collectAsState()
    val sendMsg = viewModel::sendMsg
    StoryDialogUi(model.value, sendMsg, isShowedDialog, closeStoryDialog)
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
private fun StoryDialogUi(
    model: Model,
    sendMsg: Message,
    isShowedDialog: Boolean,
    closeStoryDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = model.currentStory)
    if (isShowedDialog) {
        Dialog(
            onDismissRequest = { closeStoryDialog() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier.fillMaxSize().background(RDTheme.color.primaryVariant),
                contentAlignment = Alignment.TopCenter
            ) {
                OverscrollEffect {
                    HorizontalPager(
                        count = model.stories.size,
                        state = pagerState,
                        modifier = modifier.fillMaxSize()
                    ) { page ->
                        ItemStoryPager(model, sendMsg, page, pagerState)
                    }
                }
                Column(modifier.fillMaxSize()) {
                    StoryTopBar(model, closeStoryDialog)
                    Spacer(modifier.weight(1f))
                    StoryBottomBar()
                }
            }
        }
    }
}