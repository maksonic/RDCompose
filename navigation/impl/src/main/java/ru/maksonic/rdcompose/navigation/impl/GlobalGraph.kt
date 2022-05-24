package ru.maksonic.rdcompose.navigation.impl

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.feature.onboarding.view.OnboardingScreen
import ru.maksonic.rdcompose.navigation.api.NavDestination
import ru.maksonic.rdcompose.screen.main.MainScreen
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
class GlobalGraph @Inject constructor() : GlobalGraphBuilder {

    override fun globalNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>,
    ) {
        navGraphBuilder.navigation(
            route = NavDestination.route,
            startDestination = NavDestination.Onboarding.route
        ) {
            composable(
                NavDestination.Onboarding.route,
            ) {
                OnboardingScreen()
            }
            composable(
                NavDestination.Main.route
            ) {
                MainScreen()
            }
        }
    }
}

