package ru.maksonic.rdcompose.navigation.api.destination

/**
 * @Author maksonic on 23.05.2022
 */
sealed class Route(val route: String) {
    interface BottomNavRoute {
        val selectedIcon: Int
        val unselectedIcon: Int
        val labelId: Int
    }
}

object GlobalDestination : Route("root") {
    object Onboarding : Route("onboarding")
    object Main : Route("main")
    object Settings : Route("settings")
    object UserProfile : Route("user_profile")
}