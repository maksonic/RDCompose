package ru.maksonic.rdcompose.screen.podcast_list.model

import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 29.05.2022
 */
sealed class Cmd : Command {
    object InitToolbarTitle: Cmd()
    object FetchPodcastList : Cmd()
    object FetchCategoryInfo : Cmd()
}