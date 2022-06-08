package ru.maksonic.rdcompose.screen.home.model

import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 06.06.2022
 */
sealed class Cmd : Command {
    object FetchStories : Cmd()
}