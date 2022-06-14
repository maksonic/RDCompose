package ru.maksonic.rdcompose.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.AppDatabase
import ru.maksonic.rdcompose.data.DatabaseName
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdcompose.data.categories.cache.CategoryDao
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCacheToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastDao
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastsCacheDataSource
import javax.inject.Singleton

/**
 * @Author maksonic on 27.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, db: DatabaseName) = Room
        .databaseBuilder(context, AppDatabase::class.java, db.name)
        .build()

    @Singleton
    @Provides
    fun provideDatabaseName(rp: ResourceProvider): DatabaseName = DatabaseName.Base(rp)

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Singleton
    @Provides
    fun providePodcastDao(db: AppDatabase): PodcastDao = db.podcastDao()

    @Singleton
    @Provides
    fun provideCategoriesCacheDataSource(
        dao: CategoryDao,
        cacheMapper: CategoryCacheToDataMapper,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CategoriesCacheDataSource =
        CategoriesCacheDataSource.Base(dao, cacheMapper, exceptionHandler, dispatcher)

    @Singleton
    @Provides
    fun providePodcastsCacheDataSource(
        dao: PodcastDao,
        cacheMapper: PodcastCacheToDataMapper,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): PodcastsCacheDataSource =
        PodcastsCacheDataSource.Base(dao, cacheMapper, exceptionHandler, dispatcher)
}