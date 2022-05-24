package ru.maksonic.rdcompose.core.di

import javax.inject.Qualifier

/**
 * @Author maksonic on 23.05.2022
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher