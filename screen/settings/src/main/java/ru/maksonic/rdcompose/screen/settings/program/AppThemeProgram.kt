package ru.maksonic.rdcompose.screen.settings.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.core.store.AppTheme
import ru.maksonic.rdcompose.core.store.AppThemeSetting
import ru.maksonic.rdcompose.screen.settings.model.Cmd
import ru.maksonic.rdcompose.screen.settings.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
class AppThemeProgram @Inject constructor(
    private val settings: AppThemeSetting
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.SetTheme -> switchTheme(cmd)
        }
    }

    private suspend fun switchTheme(cmd: Cmd.SetTheme) {
        when (cmd.theme) {
            AppTheme.SYSTEM -> settings.setTheme(AppTheme.SYSTEM)
            AppTheme.LIGHT -> settings.setTheme(AppTheme.LIGHT)
            AppTheme.DARK -> settings.setTheme(AppTheme.DARK)
            AppTheme.HIGH_CONTRAST -> settings.setTheme(AppTheme.HIGH_CONTRAST)
        }
    }
}