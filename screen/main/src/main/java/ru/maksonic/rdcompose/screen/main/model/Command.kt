package ru.maksonic.rdcompose.screen.main.model

import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 25.05.2022
 */
sealed class Cmd: Command {
    object NavigateToSettings : Cmd()
}