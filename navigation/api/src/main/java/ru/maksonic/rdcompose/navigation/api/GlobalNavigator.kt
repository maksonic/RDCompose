package ru.maksonic.rdcompose.navigation.api

import ru.maksonic.rdcompose.core.elm.ElmNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
class GlobalNavigator @Inject constructor() : ElmNavigator(), Router {

    override fun onboardingToMain() {
        navigate(NavDestination.Main.route)
    }
}