package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.maksonic.rdcompose.core.common.KeyStore
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import ru.maksonic.rdcompose.screen.podcast_list.PodcastListScreen
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
class PodcastGraph @Inject constructor(
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder {

    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
    ) {
        navGraphBuilder.composable(
            route = CategoriesDestination.PodcastList.route,
        ) {
            PodcastListScreen()
        }
    }
}