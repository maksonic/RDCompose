package ru.maksonic.rdcompose.feature.audio_story.view.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.rdcompose.feature.audio_story.model.Model
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconCircleAction

/**
 * @Author maksonic on 07.06.2022
 */
@Composable
internal fun StoryTopBar(model: Model, closeDialog: () -> Unit, modifier: Modifier = Modifier) {

    val storyToggleSize = remember {
        mutableStateOf(0.dp)
    }
    val animate = animateDpAsState(targetValue = storyToggleSize.value)

    LaunchedEffect(storyToggleSize) {
        repeat(324) {
            delay(30)
            storyToggleSize.value = storyToggleSize.value.plus(1.dp)
        }
    }
    Row(
        modifier
            .height(RDTheme.componentSize.smallTopBarHeight)
            .background(RDTheme.color.background.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier
                .weight(1f)
                .height(5.dp)
                .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16)
                .clip(CircleShape)
                .background(RDTheme.color.divider.copy(alpha = 0.8f))
        ) {
            Box(
                modifier
                    .width(animate.value)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(RDTheme.color.onBackground)
            )
        }
        IconCircleAction(
            icId = R.drawable.ic_cancel_rounded,
            action = closeDialog,
            modifier = modifier.padding(end = RDTheme.padding.dp8)
        )
    }
}