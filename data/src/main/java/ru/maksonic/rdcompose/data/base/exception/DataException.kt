package ru.maksonic.rdcompose.data.base.exception

/**
 * @Author maksonic on 23.05.2022
 */
data class EmptyDataException(override val message: String = "") : Exception(message)
data class EmptyCacheException(override val message: String = "") : Exception(message)
data class CachedItemNotFound(override val message: String = "") : Exception(message)
data class EmptyCloudDataException(override val message: String = "") : Exception(message)
data class TimeOutException(override val message: String = "") : Exception(message)