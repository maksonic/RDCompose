package ru.maksonic.rdcompose.data.base

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.CachedItemNotFound
import ru.maksonic.rdcompose.data.base.exception.EmptyCacheException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler

/**
 * @Author maksonic on 23.05.2022
 */
interface BaseCacheDataSource<T> {
    fun fetchCacheList(): DataList<T>
    fun fetchCacheItemById(itemId: String): Flow<Result<T>>
    suspend fun insertCacheList(list: List<T>)

    abstract class Base<T : Abstract.CacheObject>(
        private val dao: BaseDao<T>,
        private val rp: ResourceProvider,
        private val ex: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCacheDataSource<T> {

        override fun fetchCacheList() = flow {
            val cacheList = dao.fetchCacheList()
            if (cacheList.isEmpty()) {
                emit(Result.failure(ex.handle(EmptyCacheException())))
            } else {
                emit(Result.success(cacheList))
            }
        }.flowOn(dispatcher)

        override fun fetchCacheItemById(itemId: String): Flow<Result<T>> = flow {
            try {
                if (itemId.isNotEmpty()) {
                    emit(Result.success(dao.fetchCacheItemByStringId(itemId).first()))
                } else {
                    emit(Result.failure(ex.handle(CachedItemNotFound())))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(dispatcher)

        override suspend fun insertCacheList(list: List<T>) {
            try {
                if (list.isNotEmpty()) {
                    dao.deleteAllCachedList(list)
                    dao.insertAll(list)
                }
            } catch (e: Exception) {
                Log.e("BaseCacheDataSource", "ERROR -> ${e.localizedMessage}")
            }
        }
    }
}