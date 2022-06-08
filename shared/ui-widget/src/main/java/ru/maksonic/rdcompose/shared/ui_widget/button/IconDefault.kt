package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 01.06.2022
 */
@Composable
fun IconDefault(
    modifier: Modifier = Modifier,
    icId: Int,
    tint: Color = RDTheme.color.controlNormal,
    ) {
    Icon(
        painterResource(id = icId),
        contentDescription = "",
        tint = tint,
        modifier = modifier
    )
}