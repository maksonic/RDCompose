package ru.maksonic.rdcompose.screen.settings.view.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 25.05.2022
 */
@Composable
fun Footer(modifier: Modifier = Modifier, appVersion: String) {
    Column(
        modifier
            .fillMaxWidth()
            .background(RDTheme.color.surface)
    ) {
        Divider(color = RDTheme.color.divider)

        Text(
            "RD Подкасты для Android $appVersion",
            style = RDTheme.typography.caption,
            color = RDTheme.color.secondaryText,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(RDTheme.padding.dp16)
        )
    }
}