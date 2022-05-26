package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.common.KeyStore
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.MainDestination
import ru.maksonic.rdcompose.screen.categories.view.CategoriesScreen
import ru.maksonic.rdcompose.screen.collections.CollectionsScreen
import ru.maksonic.rdcompose.screen.home.HomeScreen
import ru.maksonic.rdcompose.screen.podcast_list.PodcastListScreen
import javax.inject.Inject

/**
 * @Author maksonic on 25.05.2022
 */
class MainGraph @Inject constructor(
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder {

    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        isDarkMode: State<Boolean>?
    ) {
        navGraphBuilder.navigation(
            route = MainDestination.route,
            startDestination = MainDestination.Home.route
        ) {
            composable(MainDestination.Home.route) {
                HomeScreen()
            }
            composable(MainDestination.Categories.route) {
                CategoriesScreen()
            }
            composable(MainDestination.Collections.route) {
                CollectionsScreen()
            }
            composable(MainDestination.PodcastList.route.plus(args(keyStore.passedCategoryIdKey))) {
                PodcastListScreen()
            }
        }
    }
}