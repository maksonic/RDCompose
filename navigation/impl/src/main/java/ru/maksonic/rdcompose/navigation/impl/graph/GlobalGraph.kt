package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.feature.onboarding.view.OnboardingScreen
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.GlobalDestination
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.main.view.MainScreen
import ru.maksonic.rdcompose.screen.settings.view.SettingsScreen
import ru.maksonic.rdcompose.screen.user_profile.UserProfileScreen
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */

class GlobalGraph @Inject constructor(
    private val mainGraphBuilder: MainGraph,
    private val mainNavigator: MainNavigator,
) : GraphBuilder {

    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.navigation(
            route = GlobalDestination.route,
            startDestination = GlobalDestination.Onboarding.route
        ) {
            composable(
                GlobalDestination.Onboarding.route,
            ) {
                OnboardingScreen()
            }
            composable(
                GlobalDestination.Main.route
            ) {
                MainScreen(mainGraphBuilder, mainNavigator)
            }
            composable(
                GlobalDestination.Settings.route
            ) {
                SettingsScreen()
            }

            composable(
                GlobalDestination.UserProfile.route
            ) {
                UserProfileScreen()
            }
        }
    }
}
