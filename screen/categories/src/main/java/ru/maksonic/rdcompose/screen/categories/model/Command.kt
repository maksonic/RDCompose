package ru.maksonic.rdcompose.screen.categories.model

import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 26.05.2022
 */
sealed class Cmd: Command {
    object FetchCategories: Cmd()
    object RefreshCategories: Cmd()
    data class NavigateToPodcastList(val categoryId: String) : Cmd()

}