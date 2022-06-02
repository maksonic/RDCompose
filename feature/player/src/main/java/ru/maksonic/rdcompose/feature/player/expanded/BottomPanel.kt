package ru.maksonic.rdcompose.feature.player.expanded

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.feature.player.R.*
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.IconDefault
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 02.06.2022
 */
enum class PlaySpeedState(val speed: String) {
    Slow("0.5x"), Normal("1.0x"), Fast("1.5x"), Fastest("2.0x")
}

@Composable
fun BottomPanel(modifier: Modifier = Modifier) {
    val dp16 = RDTheme.padding.dp16

    val playSpeedState = remember { mutableStateOf(PlaySpeedState.Normal) }

    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16, bottom = RDTheme.padding.dp24),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier
                .size(46.dp)
                .clip(CircleShape)
                .rippleClickable(rippleColor = RDTheme.color.primary) { setPlaySpeed(playSpeedState) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                playSpeedState.value.speed,
                style = RDTheme.typography.caption,
                color = RDTheme.color.primary,
            )
        }

        IconActionButton(onClick = { }) {
            IconDefault(icId = drawable.ic_menu_more)
        }
    }
}

private fun setPlaySpeed(playSpeedState: MutableState<PlaySpeedState>) {
    when (playSpeedState.value) {
        PlaySpeedState.Slow -> playSpeedState.value = PlaySpeedState.Normal
        PlaySpeedState.Normal -> playSpeedState.value = PlaySpeedState.Fast
        PlaySpeedState.Fast -> playSpeedState.value = PlaySpeedState.Fastest
        else -> playSpeedState.value = PlaySpeedState.Slow

    }
}

