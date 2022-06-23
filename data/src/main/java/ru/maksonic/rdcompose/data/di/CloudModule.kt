package ru.maksonic.rdcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.FirestoreCategoryToCloudMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.FirestorePodcastToCloudMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import javax.inject.Singleton

/**
 * @Author maksonic on 26.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Singleton
    @Provides
    fun provideFirebaseApi(keyStore: KeyStore.DataKey): FirebaseApi = FirebaseApi.Base(keyStore)

    @Singleton
    @Provides
    fun provideCategoriesCloudDataSource(
        firestoreMapper: FirestoreCategoryToCloudMapper,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CategoriesCloudDataSource =
        CategoriesCloudDataSource(
            firestoreMapper = firestoreMapper,
            exceptionHandler = exceptionHandler,
            dispatcher = dispatcher
        )

    @Singleton
    @Provides
    fun providePodcastsCloudDataSource(
        api: FirebaseApi,
        firestoreMapper: FirestorePodcastToCloudMapper,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): PodcastsCloudDataSource =
        PodcastsCloudDataSource.Base(
            api = api,
            firestoreMapper = firestoreMapper,
            exceptionHandler = exceptionHandler,
            dispatcher = dispatcher
        )
}