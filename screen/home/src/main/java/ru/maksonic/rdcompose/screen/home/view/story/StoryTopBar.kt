package ru.maksonic.rdcompose.screen.home.view.story

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconCircleAction

/**
 * @Author maksonic on 07.06.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun StoryTopBar(
    model: Model,
    sendMsg: Message,
    scope: CoroutineScope,
    pagerState: PagerState,
    isPaused: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .height(RDTheme.componentSize.smallTopBarHeight)
            .background(RDTheme.color.background.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StoriesProgressIndicator(
            scope,
            pagerState,
            model,
            sendMsg,
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            stepDuration = 3000,
            currentStep = pagerState.currentPage,
            isPaused = isPaused.value,
        )
        IconCircleAction(
            icId = R.drawable.ic_cancel_rounded,
            action = { sendMsg(Msg.Ui.CloseStory) },
            modifier = modifier.padding(end = RDTheme.padding.dp8)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StoriesProgressIndicator(
    scope: CoroutineScope,
    pagerState: PagerState,
    model: Model,
    sendMsg: Message,
    modifier: Modifier = Modifier,
    stepDuration: Int,
    currentStep: Int,
    isPaused: Boolean = false,
) {
    val progress = remember(currentStep) { Animatable(0f) }
    val stepProgress = when {
        pagerState.currentPage == pagerState.currentPage -> progress.value
        pagerState.currentPage > model.story.currentStory -> 0f
        else -> 1f
    }
    Row(
        modifier = modifier
    ) {
        LinearProgressIndicator(
            color = RDTheme.color.onBackground,
            backgroundColor = RDTheme.color.divider,
            progress = stepProgress,
            modifier = Modifier
                .weight(1f)
                .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16)
                .height(6.dp)
                .clip(CircleShape)
        )
    }

    LaunchedEffect(
        isPaused, currentStep
    ) {
        if (isPaused) {
            progress.stop()
        } else {
            progress.animateTo(
                1f,
                animationSpec = tween(
                    durationMillis = ((1f - progress.value) * stepDuration).toInt(),
                    easing = LinearEasing
                )
            )
            if (pagerState.currentPage + 1 <= pagerState.pageCount - 1) {
                progress.snapTo(0f)
                sendMsg(Msg.Ui.OnNextStoryClicked(scope, pagerState))
            } else {
                sendMsg(Msg.Ui.CloseStory)
            }
        }
    }
}









