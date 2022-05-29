package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message

/**
 * @Author maksonic on 29.05.2022
 */
@Immutable
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object OnPodcastClicked : Ui()
    }

    sealed class Internal : Msg() {

    }
}