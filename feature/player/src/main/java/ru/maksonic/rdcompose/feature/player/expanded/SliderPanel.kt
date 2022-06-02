package ru.maksonic.rdcompose.feature.player.expanded

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 02.06.2022
 */
@Composable
fun SliderPanel(modifier: Modifier = Modifier) {
    val dp32 = RDTheme.padding.dp32
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp32, end = dp32)
    ) {
        Slider(
            value = sliderPosition,
            valueRange = 0f..100f,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = RDTheme.color.primary,
                activeTrackColor = RDTheme.color.primary,
                inactiveTrackColor = RDTheme.color.divider,
            ),
            modifier = modifier
                .fillMaxWidth()
        )

        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "0:00", style = RDTheme.typography.body, color = RDTheme.color.primary)
            Text(text = "5:00", style = RDTheme.typography.body, color = RDTheme.color.primary)
        }
    }
}