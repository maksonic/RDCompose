package ru.maksonic.rdcompose.navigation.impl.graph

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import ru.maksonic.rdcompose.screen.categories.view.CategoriesScreen
import ru.maksonic.rdcompose.screen.podcast_list.view.PodcastListScreen
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
class CategoriesGraph @Inject constructor(
    private val keyStore: KeyStore.NavigationKey
) : GraphBuilder {

    @OptIn(ExperimentalMaterialApi::class)
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        playerBottomSheetState: BottomSheetScaffoldState,
    ) {
        navGraphBuilder.navigation(
            route = CategoriesDestination.route,
            startDestination = CategoriesDestination.Categories.route,
        ) {
            composable(CategoriesDestination.Categories.route) {
                CategoriesScreen(playerBottomSheetState)
            }
            composable(
                CategoriesDestination.PodcastList.route.plus(args(keyStore.passedCategoryIdKey))
            ) {
                PodcastListScreen(playerBottomSheetState)
            }
        }
    }
}