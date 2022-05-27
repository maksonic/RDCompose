package ru.maksonic.rdcompose.screen.settings.model

import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.core.store.AppTheme

/**
 * @Author maksonic on 27.05.2022
 */
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object ShowThemeSelector: Ui()
        data class SwitchAppTheme(val theme: AppTheme) : Ui()
    }
}