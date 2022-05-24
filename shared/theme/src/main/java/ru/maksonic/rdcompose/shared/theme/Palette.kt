package ru.maksonic.rdcompose.shared.theme

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 23.05.2022
 */
val Black = Color(0xFF000000)
val CarmineRed = Color(0xFFB00020)
val DimGray = Color(0xFFEDEDED)
val GainsboroGray = Color(0xFFDCDCDC)
val Nero = Color(0xFF212121)
val Ripple = Color(0x0AFFFFFF)
val ShadowLight = Color(0x1FFFFFFF)
val ShadowDark = Color(0x52000000)
val Solitude = Color(0xFFF7F7F7)
val ShuttleGray = Color(0xFF65676B)
val Transparent = Color(0x00000000)
val White = Color(0xFFFFFFFF)

val baseLightPalette = RDColor(
    primary = Nero,
    primaryVariant = Black,
    onPrimary = White,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Solitude,
    onBackground = White,
    surface = White,
    onSurface = Solitude,
    divider = GainsboroGray,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = Black,
    secondaryText = ShuttleGray,
    tertiaryText = DimGray,
    controlNormal = Nero
)

val baseDarkPalette = RDColor(
    primary = Nero,
    primaryVariant = Black,
    onPrimary = White,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Nero,
    onBackground = White,
    surface = White,
    onSurface = Solitude,
    divider = ShadowDark,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = Black,
    secondaryText = ShuttleGray,
    tertiaryText = DimGray,
    controlNormal = Solitude
)

val baseHighContrastPalette = RDColor(
    primary = Nero,
    primaryVariant = Black,
    onPrimary = White,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Black,
    onBackground = White,
    surface = White,
    onSurface = Solitude,
    divider = ShadowDark,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = Black,
    secondaryText = ShuttleGray,
    tertiaryText = DimGray,
    controlNormal = White
)