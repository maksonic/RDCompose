package ru.maksonic.rdcompose.screen.podcast_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.screen.podcast_list.update.UpdateResult
import javax.inject.Singleton

/**
 * @Author maksonic on 26.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object PodcastListModule {

    @Singleton
    @Provides
    fun providePodcastListUpdateResult(): UpdateResult = UpdateResult.Base()
}