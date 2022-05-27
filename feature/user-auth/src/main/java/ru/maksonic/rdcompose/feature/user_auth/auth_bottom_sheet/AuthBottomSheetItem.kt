package ru.maksonic.rdcompose.feature.user_auth.auth_bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_widget.button.clickAction

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
internal fun AuthItem(modifier: Modifier = Modifier, icon: Int, title: String, action: () -> Unit) {
    Column() {
        Row(
            modifier
                .fillMaxWidth()
                .height(RDTheme.componentSize.btnPrimaryHeight)
                .clickAction { action() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                tint = RDTheme.color.controlNormal,
                contentDescription = null,
                modifier = modifier.padding(start = RDTheme.padding.dp16)
            )
            Spacer(modifier.padding(start = RDTheme.padding.dp16))
            Text(text = title, style = RDTheme.typography.body, color = RDTheme.color.primaryText)
        }

        Divider(
            modifier = Modifier
                .padding(start = RDTheme.padding.dp16, end = RDTheme.padding.dp16),
            color = RDTheme.color.divider
        )
    }
}