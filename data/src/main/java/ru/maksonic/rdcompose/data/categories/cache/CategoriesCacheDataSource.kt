package ru.maksonic.rdcompose.data.categories.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.EmptyCacheDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.CategoryData
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
typealias CategoriesCache = Flow<Result<List<CategoryCache>>>

interface CategoriesCacheDataSource {
    suspend fun saveToCache(data: List<CategoryData>)
    fun readFromCache(): CategoriesCache
    suspend fun readCategoryById(id: Long): Flow<Result<CategoryCache>>

    class Base @Inject constructor(
        private val dao: CategoryDao,
        private val cacheMapper: CategoryCacheToDataMapper,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : CategoriesCacheDataSource {

        override suspend fun saveToCache(data: List<CategoryData>) {
            if (data.isNotEmpty()) {
                val cacheList = cacheMapper.mapToList(data)
                dao.insertAll(cacheList)
            } else
                return
        }

        override fun readFromCache(): CategoriesCache = flow {
            try {
                val cacheList = dao.fetchCacheList()
                if (cacheList.isNotEmpty()) {
                    emit(Result.success(cacheList))
                } else
                    emit(Result.failure(EmptyCacheDataException()))
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)

        override suspend fun readCategoryById(id: Long): Flow<Result<CategoryCache>> =
            dao.fetchCacheItemById(id).transform { category ->
                emit(Result.success(category))
            }.flowOn(dispatcher)
                .catch { error -> emit(Result.failure(error)) }
    }
}