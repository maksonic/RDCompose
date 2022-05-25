package ru.maksonic.rdcompose.navigation.api.navigator

import ru.maksonic.rdcompose.core.elm.ElmNavigator
import ru.maksonic.rdcompose.navigation.api.Router
import ru.maksonic.rdcompose.navigation.api.destination.GlobalDestination
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
class GlobalNavigator @Inject constructor() : ElmNavigator(), Router.Global {

    override fun onboardingToMain() {
        navigate(GlobalDestination.Main.route)
    }

    override fun mainToSettings() {
        navigate(GlobalDestination.Settings.route)
    }
}