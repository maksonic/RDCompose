package ru.maksonic.rdcompose.navigation.impl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.navigation.impl.graph.*
import javax.inject.Singleton

/**
 * @Author maksonic on 23.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideGlobalGraphBuilding(
        homeGraph: HomeGraph,
        categoriesGraph: CategoriesGraph,
        collectionsGraph: CollectionsGraph,
        podcastGraph: PodcastGraph
    ): GraphBuilder = GlobalGraph(homeGraph, categoriesGraph, collectionsGraph, podcastGraph)

    @Singleton
    @Provides
    fun provideGlobalNavigator(): GlobalNavigator = GlobalNavigator()

    @Singleton
    @Provides
    fun provideMainNavigator(): MainNavigator = MainNavigator()
}