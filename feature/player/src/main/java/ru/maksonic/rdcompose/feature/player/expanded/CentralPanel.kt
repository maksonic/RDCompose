package ru.maksonic.rdcompose.feature.player.expanded

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.feature.player.R.*
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.IconActionButton
import ru.maksonic.rdcompose.shared.ui_widget.button.IconDefault
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 02.06.2022
 */
@Composable
fun CentralPlayer(modifier: Modifier = Modifier) {
    val dp32 = RDTheme.padding.dp32
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp32, end = dp32),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconActionButton(onClick = { }) {
            IconDefault(icId = drawable.ic_replay_10_sec, modifier.size(36.dp))
        }

        Box(
            modifier
                .size(RDTheme.componentSize.playerPlayIconSize)
                .clip(CircleShape)
                .background(RDTheme.color.secondary.copy(alpha = 0.2f))
                .rippleClickable(rippleColor = RDTheme.color.primary) { },
            contentAlignment = Alignment.Center
        ) {
            IconDefault(
                icId = R.drawable.ic_play_rounded,
                modifier.size(RDTheme.componentSize.playerPlayIconSize)
            )
        }

        IconActionButton(onClick = { }) {
            IconDefault(icId = drawable.ic_forward_30_sec, modifier.size(36.dp))
        }
    }
}
