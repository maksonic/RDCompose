package ru.maksonic.rdcompose.domain.base

/**
 * @Author maksonic on 21.06.2022
 */
interface BaseInteractor<T, Arg: Any> {
    suspend fun fetchData(id: Arg? = null): T
    suspend fun refreshData(id: Arg? = null): T
}