package ru.maksonic.rdcompose.shared.ui_widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 26.05.2022
 */
@Composable
fun ScreenTitleDisplay(title: String, modifier: Modifier = Modifier) {
    Text(
        title,
        style = RDTheme.typography.display,
        color = RDTheme.color.primaryText,
        modifier = modifier.padding(start = RDTheme.padding.dp16)
    )
}