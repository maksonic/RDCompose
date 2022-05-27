package ru.maksonic.rdcompose.screen.settings.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.core.store.AppTheme

/**
 * @Author maksonic on 27.05.2022
 */
data class Model(
    val isAuthUser: Boolean = false,
    val isExpandedThemeSelector: Boolean = false,
    val themeVariants: List<AppTheme> = listOf(
        AppTheme.SYSTEM,
        AppTheme.LIGHT,
        AppTheme.DARK,
        AppTheme.HIGH_CONTRAST
    ),
    val currentAppTheme: MutableState<AppTheme> = mutableStateOf(AppTheme.SYSTEM),
) : StateModel