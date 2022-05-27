package ru.maksonic.rdcompose.screen.settings.model

import ru.maksonic.rdcompose.core.elm.Command
import ru.maksonic.rdcompose.core.store.AppTheme

/**
 * @Author maksonic on 27.05.2022
 */
sealed class Cmd: Command {
    data class SetTheme(val  theme: AppTheme) : Cmd()
}