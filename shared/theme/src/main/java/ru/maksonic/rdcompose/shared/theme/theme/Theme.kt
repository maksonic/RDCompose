package ru.maksonic.rdcompose.shared.theme.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.maksonic.rdcompose.shared.theme.*
import ru.maksonic.rdcompose.shared.theme.R

/**
 * @Author maksonic on 23.05.2022
 */
@Composable
fun RDComposeTheme(
    lightPalette: RDColor = baseLightPalette,
    darkPalette: RDColor = baseDarkPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkPalette else lightPalette

    val images = RDImage(
        placeholder = if (darkTheme) R.drawable.placeholder_night else R.drawable.placeholder,
        placeholderLandscape = if (darkTheme) R.drawable.placeholder_landscape_night
        else R.drawable.placeholder_landscape
    )


    CompositionLocalProvider(
        LocalRDColors provides colors,
        LocalRDComponentSize provides componentSize,
        LocalRDElevation provides elevations,
        LocalRDImage provides images,
        LocalRDPadding provides paddings,
        LocalRDShape provides shapes,
        LocalRDTypography provides typography,
        LocalRippleTheme provides RDRipple,
        content = content
    )
}

object RDTheme {
    val color: RDColor @Composable get() = LocalRDColors.current
    val componentSize: RDComponentSize @Composable get() = LocalRDComponentSize.current
    val elevation: RDElevation @Composable get() = LocalRDElevation.current
    val image: RDImage @Composable get() = LocalRDImage.current
    val padding: RDPadding @Composable get() = LocalRDPadding.current
    val shape: RDShape @Composable get() = LocalRDShape.current
    val typography: RDTypography @Composable get() = LocalRDTypography.current
}

@Composable
fun MainTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    RDComposeTheme(
        lightPalette = baseLightPalette,
        darkPalette = baseDarkPalette,
        darkTheme,
        content
    )
}

@Composable
fun HighContrastTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    RDComposeTheme(
        lightPalette = baseHighContrastPalette,
        darkPalette = baseHighContrastPalette,
        darkTheme,
        content
    )
}
