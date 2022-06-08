package ru.maksonic.rdcompose.shared.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 23.05.2022
 */
val LocalRDColors = staticCompositionLocalOf<RDColor> {
    error("No colors provided")
}
data class RDColor(
    val primary: Color,
    val primaryVariant: Color,
    val onPrimary: Color,
    val secondary: Color,
    val secondaryVariant: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val divider: Color,
    val error: Color,
    val onError: Color,
    val transparent: Color,
    val shadow: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val onBackgroundText: Color,
    val link: Color,
    val controlNormal: Color,
    val storyBorder: Color,
    val isFavorite: Color
)