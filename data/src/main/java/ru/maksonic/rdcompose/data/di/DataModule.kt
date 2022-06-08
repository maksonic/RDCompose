package ru.maksonic.rdcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.CategoriesRepository
import ru.maksonic.rdcompose.data.categories.CategoryDataToDomainMapper
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.data.onboarding.BaseOnboardingRepository
import ru.maksonic.rdcompose.data.podcasts.PodcastDataToDomainMapper
import ru.maksonic.rdcompose.data.podcasts.PodcastsRepository
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloudToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import ru.maksonic.rdcompose.data.stories.StoriesRepository
import ru.maksonic.rdcompose.domain.base.CloudRepository
import ru.maksonic.rdcompose.domain.base.CommonRepository
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import ru.maksonic.rdcompose.domain.onboarding.OnboardingRepository
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import ru.maksonic.rdcompose.domain.stories.StoriesRepo
import javax.inject.Singleton

/**
 * @Author maksonic on 23.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideOnboardingRepository(): OnboardingRepository = BaseOnboardingRepository()

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        cloudDataSource: CategoriesCloudDataSource,
        cacheDataSource: CategoryCacheDataSource,
        cloudMapper: CategoryCloudToDataMapper,
        cacheMapper: CategoryCacheToDataMapper,
        dataToDomainMapper: CategoryDataToDomainMapper
    ): CommonRepository<CategoryDomain> = CategoriesRepository(
        cloudDataSource = cloudDataSource,
        cacheDataSource = cacheDataSource,
        cloudMapper = cloudMapper,
        cacheMapper = cacheMapper,
        dataToDomainMapper = dataToDomainMapper
    )

    @Singleton
    @Provides
    fun providePodcastsRepository(
        cloudDataSource: PodcastsCloudDataSource,
        cloudMapper: PodcastCloudToDataMapper,
        dataToDomainMapper: PodcastDataToDomainMapper
    ): CloudRepository<PodcastDomain> = PodcastsRepository(
        cloudDataSource = cloudDataSource,
        cloudMapper = cloudMapper,
        dataToDomainMapper = dataToDomainMapper
    )

    @Singleton
    @Provides
    fun provideStoriesRepository(): StoriesRepo = StoriesRepository()

    @Singleton
    @Provides
    fun provideExceptionHandler(rp: ResourceProvider): ExceptionHandler = ExceptionHandler.Base(rp)
}