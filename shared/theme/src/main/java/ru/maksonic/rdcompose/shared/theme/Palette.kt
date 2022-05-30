package ru.maksonic.rdcompose.shared.theme

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 23.05.2022
 */
val Black = Color(0xFF000000)
val Blue = Color(0xFF0000EE)
val CarmineRed = Color(0xFFB00020)
val CodGray = Color(0xFF171717)
val DimGray = Color(0xFFEDEDED)
val DodgerBlue = Color(0xFF31BDFF)
val GainsboroGray = Color(0xFFDCDCDC)
val Nero = Color(0xFF212121)
val MineShaft = Color(0xFF313131)
val Ripple = Color(0x0AFFFFFF)
val ShadowLight = Color(0x43FFFFFF)
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
    onBackgroundText = White,
    link = Blue,
    controlNormal = Nero
)

val baseDarkPalette = RDColor(
    primary = Solitude,
    primaryVariant = Black,
    onPrimary = Nero,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Nero,
    onBackground = White,
    surface = MineShaft,
    onSurface = Solitude,
    divider = ShuttleGray,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = White,
    secondaryText = ShuttleGray,
    tertiaryText = DimGray,
    onBackgroundText = Solitude,
    link = DodgerBlue,
    controlNormal = Solitude
)

val baseHighContrastPalette = RDColor(
    primary = White,
    primaryVariant = Black,
    onPrimary = Black,
    secondary = ShuttleGray,
    secondaryVariant = DimGray,
    background = Black,
    onBackground = White,
    surface = CodGray,
    onSurface = Solitude,
    divider = Nero,
    error = CarmineRed,
    onError = White,
    transparent = Transparent,
    shadow = ShadowDark,
    primaryText = White,
    secondaryText = DimGray,
    tertiaryText = DimGray,
    onBackgroundText = White,
    link = DodgerBlue,
    controlNormal = White
)