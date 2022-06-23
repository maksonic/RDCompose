package ru.maksonic.rdcompose.data.base

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.EmptyCacheDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.domain.base.DataItem
import ru.maksonic.rdcompose.domain.base.DataList

/**
 * @Author maksonic on 20.06.2022
 */
interface BaseCacheDataSource<T: Abstract.CacheObject> {
    fun readCacheList(cacheList: List<T>): DataList<T>
    fun readCacheItemById(id: Long): DataItem<T>
    suspend fun saveListToCache(list: List<T>)

    abstract class Base<T : Abstract.CacheObject>(
        private val dao: BaseDao<T>,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ): BaseCacheDataSource<T> {

        override fun readCacheList(cacheList: List<T>): DataList<T> = flow {
            if (cacheList.isNotEmpty())
                emit(Result.success(cacheList))
            else
                emit(Result.failure(exceptionHandler.handle(EmptyCacheDataException())))
        }.flowOn(dispatcher)

        override fun readCacheItemById(id: Long): DataItem<T> = flow {
            try {
                val findItem = dao.fetchCacheItemById(id).first()
                emit(Result.success(findItem))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(dispatcher)

        override suspend fun saveListToCache(list: List<T>) {
            try {
                withContext(dispatcher) {
                    if (list.isNotEmpty())
                        dao.deleteAllCachedList(dao.fetchCacheList())
                        dao.insertAll(list)
                }
            } catch (e: Exception) {
                Log.e("BaseCacheDataSource", "ERROR -> ${e.localizedMessage}")
            }
        }
    }
}