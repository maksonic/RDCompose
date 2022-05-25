package ru.maksonic.rdcompose.screen.main.program

import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.screen.main.model.Cmd
import ru.maksonic.rdcompose.screen.main.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 25.05.2022
 */
class NavigationProgram @Inject constructor(
    private val navigator: GlobalNavigator
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.NavigateToSettings -> navigator.mainToSettings()
        }
    }
}