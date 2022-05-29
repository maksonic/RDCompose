package ru.maksonic.rdcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.data.FirebaseApi
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