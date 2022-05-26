package ru.maksonic.rdcompose.screen.categories

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.screen.categories.update.UpdateResult
import javax.inject.Singleton

/**
 * @Author maksonic on 26.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CategoriesModule {

    @Singleton
    @Provides
    fun provideCategoriesUpdateResult(): UpdateResult = UpdateResult.Base()
}