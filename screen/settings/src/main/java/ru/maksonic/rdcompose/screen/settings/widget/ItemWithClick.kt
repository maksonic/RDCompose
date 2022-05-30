package ru.maksonic.rdcompose.screen.settings.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun ItemWithClick(
    modifier: Modifier = Modifier,
    backgroundColor: Color = RDTheme.color.surface,
    title: String,
    icon: Int,
    action: () -> Unit,
    rotateIcon: Float = 0f,
) {
    val dp8 = RDTheme.padding.dp8
    val dp12 = RDTheme.padding.dp12
    val dp16 = RDTheme.padding.dp16

    Row(
        modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .rippleClickable(rippleColor = RDTheme.color.primary) { action() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = RDTheme.color.controlNormal,
            contentDescription = "",
            modifier = modifier
                .padding(top = dp12, bottom = dp12, start = dp16)
        )

        Text(
            text = title,
            style = RDTheme.typography.title,
            color = RDTheme.color.primaryText,
            modifier = modifier.padding(start = dp16)
        )
        Spacer(modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_round_forward_24),
            tint = RDTheme.color.controlNormal,
            contentDescription = "",
            modifier = modifier
                .padding(end = dp8)
                .rotate(rotateIcon)

        )
    }
}