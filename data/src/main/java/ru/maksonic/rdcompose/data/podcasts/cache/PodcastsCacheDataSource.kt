package ru.maksonic.rdcompose.data.podcasts.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.EmptyCacheDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.podcasts.PodcastData
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
typealias PodcastsCache = Flow<Result<List<PodcastCache>>>

interface PodcastsCacheDataSource {
    suspend fun saveToCache(data: List<PodcastData>)
    fun readAllPodcasts(): PodcastsCache
    fun readCachePodcastsByCategoryId(categoryId: String): PodcastsCache
    fun readRecommendPodcasts(): PodcastsCache

    class Base @Inject constructor(
        private val dao: PodcastDao,
        private val cacheMapper: PodcastCacheToDataMapper,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : PodcastsCacheDataSource {
        override suspend fun saveToCache(data: List<PodcastData>) {
            if (data.isNotEmpty()) {
                val cacheList = cacheMapper.mapToList(data)
                dao.insertAll(cacheList)
            } else
                return
        }

        override fun readRecommendPodcasts(): PodcastsCache = flow {
            try {
                val findPodcasts = dao.fetchRecommendPodcasts()
                if (findPodcasts.isNotEmpty()) {
                    emit(Result.success(findPodcasts))
                } else {
                    emit(Result.failure(exceptionHandler.handle(EmptyCacheDataException())))
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))

            }
        }.flowOn(dispatcher)

        override fun readAllPodcasts(): PodcastsCache = flow {
            try {
                val findPodcasts = dao.fetchCacheList()
                if (findPodcasts.isNotEmpty()) {
                    emit(Result.success(findPodcasts))
                } else {
                    emit(Result.failure(exceptionHandler.handle(EmptyCacheDataException())))
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)

        override fun readCachePodcastsByCategoryId(categoryId: String): PodcastsCache = flow {
            try {
                val findPodcasts = dao.fetchCachePodcastsByCategoryId(categoryId)
                if (findPodcasts.isNotEmpty()) {
                    emit(Result.success(findPodcasts))
                } else {
                    emit(Result.failure(exceptionHandler.handle(EmptyCacheDataException())))
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)
    }
}