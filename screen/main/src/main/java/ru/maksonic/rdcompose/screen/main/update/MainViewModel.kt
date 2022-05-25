package ru.maksonic.rdcompose.screen.main.update

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.main.model.Cmd
import ru.maksonic.rdcompose.screen.main.model.Model
import ru.maksonic.rdcompose.screen.main.model.Msg
import ru.maksonic.rdcompose.screen.main.program.NavigationProgram
import javax.inject.Inject

/**
 * @Author maksonic on 25.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class MainViewModel @Inject constructor(
    navProgram: NavigationProgram,
    navigator: GlobalNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(""),
    initialCmd = setOf(),
    subscriptions = listOf(navProgram),
    navigator = navigator,
) {
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.ShowSettings -> model to setOf(Cmd.NavigateToSettings)
            is Msg.Ui.ShowUserProfile -> model to setOf(Cmd.NavigateToUserProfile)
        }
}