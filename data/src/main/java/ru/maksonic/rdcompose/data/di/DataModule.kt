package ru.maksonic.rdcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.CategoriesRepositoryImpl
import ru.maksonic.rdcompose.data.categories.CategoryDataToDomainMapper
import ru.maksonic.rdcompose.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.data.onboarding.BaseOnboardingRepository
import ru.maksonic.rdcompose.data.podcasts.PodcastDataToDomainMapper
import ru.maksonic.rdcompose.data.podcasts.PodcastRepositoryImpl
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCacheToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastsCacheDataSource
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloudToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import ru.maksonic.rdcompose.data.stories.StoriesRepository
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import ru.maksonic.rdcompose.domain.onboarding.OnboardingRepository
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
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
    fun provideStoriesRepository(@IoDispatcher dp: CoroutineDispatcher): StoriesRepo =
        StoriesRepository(dp)

    @Singleton
    @Provides
    fun provideExceptionHandler(rp: ResourceProvider): ExceptionHandler = ExceptionHandler.Base(rp)

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        cloudSource: CategoriesCloudDataSource,
        cacheSource: CategoriesCacheDataSource,
        cloudMapper: CategoryCloudToDataMapper,
        cacheMapper: CategoryCacheToDataMapper,
        domainMapper: CategoryDataToDomainMapper,
    ): CategoriesRepository = CategoriesRepositoryImpl(
        cloudSource = cloudSource,
        cacheSource = cacheSource,
        cloudMapper = cloudMapper,
        cacheMapper = cacheMapper,
        domainMapper = domainMapper,
    )

    @Singleton
    @Provides
    fun providePodcastsRepository(
        cloudSource: PodcastsCloudDataSource,
        cacheSource: PodcastsCacheDataSource,
        cloudMapper: PodcastCloudToDataMapper,
        cacheMapper: PodcastCacheToDataMapper,
        domainMapper: PodcastDataToDomainMapper,
    ): PodcastsRepository = PodcastRepositoryImpl(
        cloudSource = cloudSource,
        cacheSource = cacheSource,
        cloudMapper = cloudMapper,
        cacheMapper = cacheMapper,
        domainMapper = domainMapper,
    )
}