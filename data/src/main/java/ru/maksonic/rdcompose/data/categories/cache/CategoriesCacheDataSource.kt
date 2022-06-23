package ru.maksonic.rdcompose.data.categories.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.BaseCacheDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
interface CategoriesCacheDataSource {
    suspend fun saveToCache(data: List<CategoryCache>)
    suspend fun readFromCache(): CategoriesCache
    suspend fun readCategoryById(id: Long): CategoryCacheItem

    class Base @Inject constructor(
        private val dao: CategoryDao,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) : BaseCacheDataSource.Base<CategoryCache>(
        dao, exceptionHandler, dispatcher
    ), CategoriesCacheDataSource {

        override suspend fun saveToCache(data: List<CategoryCache>) = saveListToCache(data)

        override suspend fun readFromCache(): CategoriesCache = readCacheList(dao.fetchCacheList())

        override suspend fun readCategoryById(id: Long) = readCacheItemById(id)
    }
}
