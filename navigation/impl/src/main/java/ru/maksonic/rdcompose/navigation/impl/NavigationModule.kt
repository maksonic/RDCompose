package ru.maksonic.rdcompose.navigation.impl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.navigation.api.GraphBuilder
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.navigation.impl.graph.GlobalGraph
import ru.maksonic.rdcompose.navigation.impl.graph.MainGraph
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
        mainGraph: MainGraph,
        mainNavigator: MainNavigator
    ): GraphBuilder = GlobalGraph(mainGraph, mainNavigator)

    @Singleton
    @Provides
    fun provideMainGraphBuilding(): GraphBuilder = MainGraph()

    @Singleton
    @Provides
    fun provideGlobalNavigator(): GlobalNavigator = GlobalNavigator()
}