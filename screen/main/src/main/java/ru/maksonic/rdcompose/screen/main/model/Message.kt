package ru.maksonic.rdcompose.screen.main.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message

/**
 * @Author maksonic on 25.05.2022
 */
@Immutable
sealed class Msg : Message {

    sealed class Ui : Msg() {
        object ShowSettings : Ui()
    }
}