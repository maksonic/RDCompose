package ru.maksonic.rdcompose.shared.ui_widget.button

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 01.06.2022
 */
@Composable
fun IconDefault(icId: Int, modifier: Modifier = Modifier) {
    Icon(
        painterResource(id = icId),
        contentDescription = "",
        tint = RDTheme.color.controlNormal,
        modifier = modifier
    )
}