package ru.maksonic.rdcompose.shared.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 23.05.2022
 */
val LocalRDComponentSize = staticCompositionLocalOf<RDComponentSize> {
    error("No component size provided")
}

data class RDComponentSize(
    val btnNav: Dp,
    val btnPrimaryHeight: Dp,
    val btnIconCircle: Dp,
    val circularProgressIndicatorSize: Dp,
    val circularProgressIndicatorStrokeWidth: Dp,
    val enteredMaxLength: Int,
    val itemNormalListHeight: Dp,
    val smallTopBarHeight: Dp,
    val playerCollapsedHeight: Dp,
    val playerSheetPeekHeight: Dp,
    val playerPlayIconSize: Dp,
    val bottomBarStoryHeight: Dp,
)

val componentSize = RDComponentSize(
    btnNav = 24.dp,
    btnPrimaryHeight = 54.dp,
    btnIconCircle = 48.dp,
    circularProgressIndicatorSize = 100.dp,
    circularProgressIndicatorStrokeWidth = 5.dp,
    enteredMaxLength = 50,
    itemNormalListHeight = 64.dp,
    smallTopBarHeight = 64.dp,
    playerCollapsedHeight = 64.dp,
    playerSheetPeekHeight = 144.dp,
    playerPlayIconSize = 64.dp,
    bottomBarStoryHeight = 96.dp
)