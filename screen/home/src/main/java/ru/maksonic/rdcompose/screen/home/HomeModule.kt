package ru.maksonic.rdcompose.screen.home

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.screen.home.update.UpdateResult
import javax.inject.Singleton

/**
 * @Author maksonic on 10.06.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeUpdateResult(): UpdateResult = UpdateResult.Base()
}