package ru.maksonic.rdcompose.shared.ui_widget.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.RDSurface
import ru.maksonic.rdcompose.shared.ui_widget.button.clickAction

/**
 * @Author maksonic on 28.05.2022
 */
@Composable
fun CardPrimary(
    modifier: Modifier = Modifier,
    backgroundColor: Color = RDTheme.color.surface,
    shape: Shape = RoundedCornerShape(0.dp),
    elevation: Dp = 1.dp,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    RDSurface(
        shape = shape,
        elevation = elevation,
        modifier = modifier,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .clickAction { onClick() }) {
            content()
        }
    }
}