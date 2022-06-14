package ru.maksonic.rdcompose.shared.ui_widget.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 24.05.2022
 */
@Composable
fun IndicatorBottomSheet(modifier: Modifier = Modifier) {
    Row(modifier
        .fillMaxWidth()
        .height(24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Box(modifier
            .height(4.dp)
            .width(36.dp)
            .clip(RoundedCornerShape(100))
            .background(RDTheme.color.divider))
    }
}