package ru.maksonic.rdcompose.screen.settings.update

import androidx.compose.runtime.mutableStateOf
import ru.maksonic.rdcompose.screen.settings.model.Cmd
import ru.maksonic.rdcompose.screen.settings.model.Model
import ru.maksonic.rdcompose.screen.settings.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
interface UpdateResult {
    fun themeSwitching(model: Model, msg: Msg.Ui.SwitchAppTheme): Update
    fun themeSelectorState(model: Model, msg: Msg.Ui.ShowThemeSelector): Update

    class Base @Inject constructor() : UpdateResult {

        override fun themeSwitching(model: Model, msg: Msg.Ui.SwitchAppTheme): Update =
            model.copy(currentAppTheme = mutableStateOf(msg.theme)) to setOf(
                Cmd.SetTheme(msg.theme)
            )

        override fun themeSelectorState(model: Model, msg: Msg.Ui.ShowThemeSelector): Update =
            model.copy(isExpandedThemeSelector = !model.isExpandedThemeSelector) to emptySet()
    }
}