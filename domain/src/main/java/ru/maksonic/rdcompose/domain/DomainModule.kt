package ru.maksonic.rdcompose.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import ru.maksonic.rdcompose.domain.categories.interactor.FetchCategoriesInteractor
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import ru.maksonic.rdcompose.domain.podcasts.interactor.FetchPodcastsByCategoryInteractor
import ru.maksonic.rdcompose.domain.podcasts.interactor.FetchHomeContentInteractor
import javax.inject.Singleton

/**
 * @Author maksonic on 21.06.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideCategoriesInteractor(repository: CategoriesRepository): FetchCategoriesInteractor =
        FetchCategoriesInteractor(repository)

    @Singleton
    @Provides
    fun providePodcastsInteractor(repository: PodcastsRepository): FetchPodcastsByCategoryInteractor =
        FetchPodcastsByCategoryInteractor(repository)

    @Singleton
    @Provides
    fun provideHomeContentInteractor(repository: PodcastsRepository): FetchHomeContentInteractor =
        FetchHomeContentInteractor(repository)
}