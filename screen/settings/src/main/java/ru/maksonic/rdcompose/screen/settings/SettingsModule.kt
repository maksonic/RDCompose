package ru.maksonic.rdcompose.screen.settings

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.screen.settings.update.UpdateResult
import javax.inject.Singleton

/**
 * @Author maksonic on 27.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Singleton
    @Provides
    fun provideCategoriesUpdateResult(): UpdateResult = UpdateResult.Base()
}