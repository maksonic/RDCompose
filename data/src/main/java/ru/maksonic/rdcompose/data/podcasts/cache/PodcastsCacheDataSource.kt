package ru.maksonic.rdcompose.data.podcasts.cache

import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.BaseCacheDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
interface PodcastsCacheDataSource {
    suspend fun saveToCache(data: List<PodcastCache>)
    suspend fun readCachePodcastsByCategory(categoryId: Long): PodcastsCache
    suspend fun readNewPodcasts(): PodcastsCache
    suspend fun readRecommendPodcasts(): PodcastsCache
    suspend fun readTopPodcasts(): PodcastsCache
    suspend fun readRandomPodcasts(): PodcastsCache

    class Base @Inject constructor(
        private val dao: PodcastDao,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCacheDataSource.Base<PodcastCache>(
        dao, exceptionHandler, dispatcher
    ), PodcastsCacheDataSource {
        override suspend fun saveToCache(data: List<PodcastCache>) = saveListToCache(data)

        override suspend fun readCachePodcastsByCategory(categoryId: Long): PodcastsCache =
            readCacheList(dao.fetchCachePodcastsByCategoryId(categoryId))

        override suspend fun readNewPodcasts() = readCacheList(dao.fetchNewPodcasts())

        override suspend fun readRecommendPodcasts() = readCacheList(dao.fetchRecommendPodcasts())

        override suspend fun readTopPodcasts() = readCacheList(dao.fetchTopPodcasts())

        override suspend fun readRandomPodcasts(): PodcastsCache =
            readCacheList(dao.fetchCacheList().shuffled().take(10))
    }
}