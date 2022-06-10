package ru.maksonic.rdcompose.screen.home.view.widget.stories

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import ru.maksonic.rdcompose.screen.home.view.Message
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
    val currentStep = pagerState.currentPage
    val progress = remember(currentStep) { Animatable(0f) }

    Row(
        modifier
            .height(RDTheme.componentSize.smallTopBarHeight)
            .background(RDTheme.color.background.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StoriesProgressIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            scope,
            pagerState,
            model,
            sendMsg,
            progress = progress,
            stepDuration = 3000,
            currentStep = currentStep,
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
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    pagerState: PagerState,
    model: Model,
    sendMsg: Message,
    progress: Animatable<Float, AnimationVector1D>,
    stepDuration: Int,
    currentStep: Int,
    isPaused: Boolean = false,
) {
    val stepProgress = when {
        pagerState.currentPage == pagerState.currentPage -> progress.value
        pagerState.currentPage > model.story.currentStory -> 0f
        else -> 1f

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
            if (progress.value > 0.9f) {
                sendMsg(Msg.Internal.ViewedCurrentStory(model.story.stories[pagerState.currentPage].isViewed))
            }

            if (pagerState.currentPage.plus(1) <= pagerState.pageCount.minus(1)) {
                progress.snapTo(0f)
                sendMsg(Msg.Ui.OnNextStoryClicked(scope, pagerState))
            } else {
                sendMsg(Msg.Ui.CloseStory)
            }
        }
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
}









