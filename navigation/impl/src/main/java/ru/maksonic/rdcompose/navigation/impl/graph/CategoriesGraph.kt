package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.common.KeyStore
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import ru.maksonic.rdcompose.screen.categories.view.CategoriesScreen
import ru.maksonic.rdcompose.screen.podcast_list.PodcastListScreen
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
class CategoriesGraph @Inject constructor(
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder.Categories {

    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        nestedGraphs: NavGraphBuilder.() -> Unit,
        navController: NavHostController,
    ) {
        navGraphBuilder.navigation(
            route = CategoriesDestination.route,
            startDestination = CategoriesDestination.Categories.route
        ) {
            composable(CategoriesDestination.Categories.route) {
                CategoriesScreen()
            }
            nestedGraphs()
        }
    }
}