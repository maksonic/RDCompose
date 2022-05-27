package ru.maksonic.rdcompose.screen.settings.update

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.core.store.AppThemeSetting
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.screen.settings.model.Cmd
import ru.maksonic.rdcompose.screen.settings.model.Model
import ru.maksonic.rdcompose.screen.settings.model.Msg
import ru.maksonic.rdcompose.screen.settings.program.AppThemeProgram
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val updateResult: UpdateResult,
    theme: AppThemeSetting,
    themeProgram: AppThemeProgram,
    navigator: GlobalNavigator,
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(currentAppTheme = mutableStateOf(theme.currentTheme.value)),
    initialCmd = emptySet(),
    subscriptions = listOf(themeProgram),
    navigator = navigator
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.SwitchAppTheme -> updateResult.themeSwitching(model, msg)
            is Msg.Ui.ShowThemeSelector -> updateResult.themeSelectorState(model, msg)
        }
}