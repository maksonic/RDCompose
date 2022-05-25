package ru.maksonic.rdcompose.screen.settings.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.R
import ru.maksonic.rdcompose.shared.ui_widget.button.clickAction
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun ItemWithClick(
    title: String,
    icon: Int,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dp8 = RDTheme.padding.dp8
    val dp12 = RDTheme.padding.dp12
    val dp16 = RDTheme.padding.dp16

    Row(
        modifier
            .fillMaxWidth()
            .rippleClickable { action() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = RDTheme.color.controlNormal,
            contentDescription = "",
            modifier = modifier.padding(top = dp12, bottom = dp12, start = dp16)
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
            modifier = modifier.padding(end = dp8)
        )
    }
}