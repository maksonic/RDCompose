package ru.maksonic.rdcompose.shared.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author maksonic on 23.05.2022
 */
val LocalRDShape = staticCompositionLocalOf<RDShape> {
    error("No shapes provided")
}

data class RDShape(
    val cornerNone: Shape,
    val cornerSmall: Shape,
    val cornerNormal: Shape,
    val cornerBig: Shape,
    val cornerExtra: Shape,
    val dp1BorderWidth: Dp,
    val dp2BorderWidth: Dp,
    val emojiBig: TextUnit
)

val shapes = RDShape(
    cornerNone = RoundedCornerShape(0.dp),
    cornerSmall = RoundedCornerShape(4.dp),
    cornerNormal = RoundedCornerShape(8.dp),
    cornerBig = RoundedCornerShape(16.dp),
    cornerExtra = RoundedCornerShape(24.dp),
    dp1BorderWidth = 1.dp,
    dp2BorderWidth = 2.dp,
    emojiBig = 56.sp
)