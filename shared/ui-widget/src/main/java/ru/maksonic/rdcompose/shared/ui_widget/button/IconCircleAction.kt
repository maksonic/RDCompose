package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 07.06.2022
 */
@Composable
fun IconCircleAction(
    modifier: Modifier = Modifier,
    icId: Int,
    tint: Color = RDTheme.color.controlNormal,
    action: () -> Unit,
) {
    Box(
        modifier
            .size(RDTheme.componentSize.btnIconCircle)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(RDTheme.color.background.copy(alpha = 0.6f))
            .rippleClickable { action() },
        contentAlignment = Alignment.Center
    ) {
        IconDefault(icId = icId, tint = tint)
    }
}
