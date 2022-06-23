package ru.maksonic.rdcompose.domain.base

/**
 * @Author maksonic on 21.06.2022
 */
interface Repository<T> {
   suspend fun fetchData(id: Long? = null): DataList<T>
    suspend fun refreshData(id: Long? = null): DataList<T>
    suspend fun fetchItemById(id: Long): DataItem<T>
}