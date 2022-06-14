package ru.maksonic.rdcompose.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.store.AppDataStore
import ru.maksonic.rdcompose.core.store.AppThemeSetting
import javax.inject.Singleton

/**
 * @Author maksonic on 23.05.2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideDataStore(): AppDataStore = AppDataStore.ThemeSetting()

    @Singleton
    @Provides
    fun provideAppTheme(
        @ApplicationContext context: Context,
        dataStore: AppDataStore,
        keyStore: KeyStore
    ): AppThemeSetting =
        AppThemeSetting.Base(context, dataStore, keyStore)

    @Singleton
    @Provides
    fun provideKeyStore(): KeyStore = KeyStore.Base()

    @Singleton
    @Provides
    fun provideDataKeyStore(): KeyStore.DataKey = KeyStore.Data()

    @Singleton
    @Provides
    fun provideNavKeyStore(): KeyStore.NavigationKey = KeyStore.NavigationPassedKey()

    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)
}