package ru.maksonic.rdcompose.navigation.api.navigator

import ru.maksonic.rdcompose.core.elm.ElmNavigator
import ru.maksonic.rdcompose.navigation.api.Router
import ru.maksonic.rdcompose.navigation.api.destination.CategoriesDestination
import javax.inject.Inject

/**
 * @Author maksonic on 25.05.2022
 */
class MainNavigator @Inject constructor() : ElmNavigator(), Router.Main {

    override fun categoriesToCategoryPodcasts(
        categoryId: String,
        categoryName: String,
    ) {
        navigate(
            CategoriesDestination.PodcastList.route
                .plus(
                    CategoriesDestination.PodcastList.id(categoryId)
                        .plus(CategoriesDestination.PodcastList.name(categoryName))
                )
        )
    }
}