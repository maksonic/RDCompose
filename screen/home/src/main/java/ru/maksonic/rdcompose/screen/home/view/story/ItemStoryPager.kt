package ru.maksonic.rdcompose.screen.home.view.story

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 07.06.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemStoryPager(
    model: Model,
    sendMsg: Message,
    scope: CoroutineScope,
    page: Int,
    pager: PagerState,
    isPaused: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()

    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val imageModifier = Modifier
            .fillMaxSize()
            .padding(
                top = RDTheme.componentSize.smallTopBarHeight,
                bottom = RDTheme.componentSize.bottomBarStoryHeight
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        if (offset.x < screenWidth / 2) {
                            sendMsg(Msg.Ui.OnPreviousStoryClicked(scope, pager))
                        } else {
                            sendMsg(Msg.Ui.OnNextStoryClicked(scope, pager))
                        }
                        isPaused.value = false
                    },
                    onPress = {
                        try {
                            isPaused.value = true
                            awaitRelease()
                        } finally {
                            isPaused.value = false
                        }
                    }
                )
            }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            GlideImage(
                imageModel = model.story.stories[page].storyBackground,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize(1f)
            )
            Box(
                modifier
                    .fillMaxSize()
                    .background(RDTheme.color.primary.copy(alpha = 0.5f))
            )
        } else {
            GlideImage(
                imageModel = model.story.stories[page].storyBackground,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize(1f)
                    .blur(15.dp)
            )
        }

        GlideImage(
            imageModel = model.story.stories[page].storyBackground,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
    }
}