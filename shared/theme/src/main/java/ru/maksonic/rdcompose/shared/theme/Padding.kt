package ru.maksonic.rdcompose.shared.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 23.05.2022
 */
val LocalRDPadding = staticCompositionLocalOf<RDPadding> {
    error("No paddings provided")
}

data class RDPadding(
    val dp4: Dp,
    val dp8: Dp,
    val dp16: Dp,
    val dp24: Dp,
    val dp32: Dp,
    val dp54: Dp,
    val dp64: Dp,
    val dp72: Dp,
)

val paddings = RDPadding(
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp16 = 16.dp,
    dp24 = 24.dp,
    dp32 = 32.dp,
    dp54 = 54.dp,
    dp64 = 64.dp,
    dp72 = 72.dp
)