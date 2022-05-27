package ru.maksonic.rdcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.categories.CategoriesRepository
import ru.maksonic.rdcompose.data.categories.CategoryDataToDomainMapper
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.domain.base.Repository
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Singleton

/**
 * @Author maksonic on 26.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Singleton
    @Provides
    fun provideFirebaseApi(): FirebaseApi = FirebaseApi.Base()
}