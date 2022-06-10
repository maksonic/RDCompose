package ru.maksonic.rdcompose.screen.home.view.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 10.06.2022
 */
@Composable
fun ItemHeader(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(end = RDTheme.padding.dp16),
        horizontalArrangement = Arrangement.End
    ) {
        Text(title, style = RDTheme.typography.header, color = RDTheme.color.primaryText)
    }
}