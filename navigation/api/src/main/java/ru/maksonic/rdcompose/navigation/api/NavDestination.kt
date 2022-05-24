package ru.maksonic.rdcompose.navigation.api

/**
 * @Author maksonic on 23.05.2022
 */
sealed class Route(val route: String)

object NavDestination: Route("root") {
    object Onboarding : Route("onboarding")
    object Main : Route("main")
}