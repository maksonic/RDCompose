package ru.maksonic.rdcompose.shared.theme.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.maksonic.rdcompose.core.store.AppTheme
import ru.maksonic.rdcompose.shared.theme.baseDarkPalette
import ru.maksonic.rdcompose.shared.theme.baseHighContrastPalette
import ru.maksonic.rdcompose.shared.theme.baseLightPalette

/**
 * @Author maksonic on 27.05.2022
 */
@Composable
fun SystemComponentsColor(
    isDarkMode: Boolean,
    themeType: State<AppTheme>,
    systemUiController: SystemUiController = rememberSystemUiController(),
    actualBackgroundColor: Color
) {
    val minLuminanceForDarkIcons = .5f

    SideEffect {
        systemUiController.setStatusBarColor(
            color = setSystemComponentColor(isDarkMode, themeType),
            darkIcons = actualBackgroundColor.luminance() > minLuminanceForDarkIcons
        )

        systemUiController.setNavigationBarColor(
            color = setSystemComponentColor(isDarkMode, themeType),
            darkIcons = actualBackgroundColor.luminance() > minLuminanceForDarkIcons,
            navigationBarContrastEnforced = false
        )
    }
}

private fun setSystemComponentColor(
    isDarkMode: Boolean,
    themeType: State<AppTheme>
): Color = when (themeType.value) {
    AppTheme.LIGHT -> baseLightPalette.background
    AppTheme.DARK -> baseDarkPalette.background
    AppTheme.HIGH_CONTRAST -> baseHighContrastPalette.background
    AppTheme.SYSTEM -> {
        if (isDarkMode) baseDarkPalette.background else baseLightPalette.background
    }
}