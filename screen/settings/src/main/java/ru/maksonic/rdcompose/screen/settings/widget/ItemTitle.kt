package ru.maksonic.rdcompose.screen.settings.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
internal fun SettingTitle(title: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(
            title,
            style = RDTheme.typography.header,
            color = RDTheme.color.primaryText,
            modifier = modifier.padding(
                start = RDTheme.padding.dp16,
                top = RDTheme.padding.dp16,
                bottom = RDTheme.padding.dp16
            )
        )
    }

}