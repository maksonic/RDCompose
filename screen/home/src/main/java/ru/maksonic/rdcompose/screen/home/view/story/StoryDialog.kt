package ru.maksonic.rdcompose.screen.home.view.story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect

/**
 * @Author maksonic on 07.06.2022
 */
internal typealias Message = (Msg) -> Unit

@Composable
fun StoryDialog(model: Model, sendMsg: Message) {

    StoryDialogUi(model, sendMsg)
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalPagerApi::class)
@Composable
private fun StoryDialogUi(
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier
) {

    if (model.story.isShowedStoryDialog) {
        val pagerState = rememberPagerState(initialPage = model.story.currentStory)
        val scope = rememberCoroutineScope()
        val isPaused = remember { mutableStateOf(false) }

        Dialog(
            onDismissRequest = { sendMsg(Msg.Ui.CloseStory) },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier
                    .fillMaxSize()
                    .background(RDTheme.color.primaryVariant),
                contentAlignment = Alignment.TopCenter
            ) {
                OverscrollEffect {
                    HorizontalPager(
                        count = model.story.stories.size,
                        state = pagerState,
                        modifier = modifier.fillMaxSize()
                    ) { page ->
                        ItemStoryPager(model, sendMsg, scope, page, pagerState, isPaused)
                    }
                }
                Column(modifier.fillMaxSize()) {
                    StoryTopBar(model, sendMsg, scope, pagerState, isPaused)
                    Spacer(modifier.weight(1f))
                    StoryBottomBar()
                }
            }
        }
    }
}