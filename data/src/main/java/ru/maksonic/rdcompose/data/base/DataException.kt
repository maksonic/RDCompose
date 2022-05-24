package ru.maksonic.rdcompose.data.base

/**
 * @Author maksonic on 23.05.2022
 */
data class EmptyCacheException(override val message: String) : Exception(message)