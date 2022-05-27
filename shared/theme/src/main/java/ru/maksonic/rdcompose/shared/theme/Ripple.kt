package ru.maksonic.rdcompose.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme

/**
 * @Author maksonic on 25.05.2022
 */
object RDRipple : RippleTheme {
    @Composable override fun defaultColor(): Color = RDTheme.color.onPrimary

   @Composable override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
       RDTheme.color.primary,
       lightTheme = !isSystemInDarkTheme()
   )
}