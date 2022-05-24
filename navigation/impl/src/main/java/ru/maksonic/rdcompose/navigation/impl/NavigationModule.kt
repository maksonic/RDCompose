package ru.maksonic.rdcompose.navigation.impl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.navigation.api.GlobalNavigator
import javax.inject.Singleton

/**
 * @Author maksonic on 23.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideMainGraphBuilding(): GlobalGraphBuilder = GlobalGraph()

    @Singleton
    @Provides
    fun provideGlobalNavigator(): GlobalNavigator = GlobalNavigator()
}