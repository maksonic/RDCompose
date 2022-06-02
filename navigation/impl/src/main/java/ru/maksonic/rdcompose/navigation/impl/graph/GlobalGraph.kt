package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.feature.onboarding.view.OnboardingScreen
import ru.maksonic.rdcompose.feature.privacy.PrivacyScreen
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.GlobalDestination
import ru.maksonic.rdcompose.screen.main.view.MainScreen
import ru.maksonic.rdcompose.screen.settings.view.SettingsScreen
import ru.maksonic.rdcompose.screen.user_profile.UserProfileScreen
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */

class GlobalGraph @Inject constructor(
    private val homeGraphBuilder: HomeGraph,
    private val categoriesGraphBuilder: CategoriesGraph,
    private val collectionsGraphBuilder: CollectionsGraph,
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder {

    @OptIn(ExperimentalMaterialApi::class)
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        playerBottomSheetState: BottomSheetScaffoldState
    ) {
        navGraphBuilder.navigation(
            route = GlobalDestination.route,
            startDestination = GlobalDestination.Onboarding.route,
        ) {
            composable(
                GlobalDestination.Onboarding.route,
            ) {
                OnboardingScreen()
            }
            composable(
                GlobalDestination.PrivacyPolicy.route.plus(args(keyStore.passedPrivacyKey)),
            ) {
                PrivacyScreen()
            }
            composable(
                GlobalDestination.Main.route,
            ) {
                MainScreen(
                    homeGraphBuilder = homeGraphBuilder,
                    categoriesGraphBuilder = categoriesGraphBuilder,
                    collectionsGraphBuilder = collectionsGraphBuilder,
                    playerBottomSheetState = playerBottomSheetState,
                )
            }
            composable(
                GlobalDestination.Settings.route,
            ) {
                SettingsScreen()
            }

            composable(
                GlobalDestination.UserProfile.route,
            ) {
                UserProfileScreen()
            }
        }
    }
}
