package ru.maksonic.rdcompose.data.podcasts

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.BaseRepository
import ru.maksonic.rdcompose.data.base.exception.EmptyDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCache
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCacheToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastDao
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastsCacheDataSource
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloud
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloudToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import ru.maksonic.rdcompose.domain.podcasts.*
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
class PodcastRepositoryImpl @Inject constructor(
    private val api: FirebaseApi,
    private val dao: PodcastDao,
    private val cloudSource: PodcastsCloudDataSource.Base,
    private val cacheSource: PodcastsCacheDataSource.Base,
    private val cloudMapper: PodcastCloudToDataMapper,
    private val cacheMapper: PodcastCacheToDataMapper,
    private val domainMapper: PodcastDataToDomainMapper,
    private val exceptionHandler: ExceptionHandler,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseRepository<PodcastCloud, PodcastCache, PodcastData, PodcastDomain>(
    cloudSource, cacheSource, cloudMapper, cacheMapper, domainMapper
), PodcastsRepository {

    override val snapshot: suspend () -> QuerySnapshot = { api.getPodcasts() }
    override suspend fun cacheList(): List<PodcastCache> = dao.fetchCacheList()

    override suspend fun fetchPodcastByCategory(categoryId: Long): Podcasts =
        cacheSource.readCachePodcastsByCategory(categoryId).transform { cacheResponse ->
            cacheResponse.onSuccess { cacheList ->
                val podcasts = domainMapper.mapFromList(cacheMapper.mapFromList(cacheList))
                emit(Result.success(podcasts))
            }
            cacheResponse.onFailure {
                refreshPodcastByCategory(categoryId).collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override suspend fun refreshPodcastByCategory(categoryId: Long): Podcasts =
        cloudSource.fetchPodcastsByCategoryId(categoryId).transform { cloudResponse ->
            cloudResponse.onSuccess { podcastsCloud ->
                val podcastsData = cloudMapper.mapFromList(podcastsCloud)
                val podcastsCache = cacheMapper.mapToList(podcastsData)
                val podcasts = domainMapper.mapFromList(podcastsData)
                cacheSource.saveToCache(podcastsCache)
                emit(Result.success(podcasts))
            }
            cloudResponse.onFailure { error -> emit(Result.failure(error)) }
        }

    override suspend fun fetchHomeContent(): HomeContent = combineTransform(
        fetchCombinedData(cacheSource.readNewPodcasts(), cloudSource.fetchNewPodcasts()),
        fetchCombinedData(
            cacheSource.readRecommendPodcasts(),
            cloudSource.fetchRecommendPodcasts()
        ),
        fetchCombinedData(cacheSource.readTopPodcasts(), cloudSource.fetchTopPodcasts()),
        fetchCombinedData(cacheSource.readRandomPodcasts(), cloudSource.fetchPodcasts())
    ) { newPodcasts, recommendPodcasts, topPodcasts, randomPodcasts ->
        val new = newPodcasts.getOrNull()?.shuffled()
        val recommend = recommendPodcasts.getOrNull()?.shuffled()
        val top = topPodcasts.getOrNull()
        val random = randomPodcasts.getOrNull()?.shuffled()?.take(10)
        if (new != null && recommend != null && top != null && random != null) {
            emit(Result.success(HomeScreenContent(new, recommend, top, random)))
        } else {
            emit(Result.failure(exceptionHandler.handle(EmptyDataException())))
        }
    }.flowOn(dispatcher)

    override suspend fun refreshHomeContent(): HomeContent = combineTransform(
        refreshCombinedData(cloudSource.fetchNewPodcasts()),
        refreshCombinedData(cloudSource.fetchRecommendPodcasts()),
        refreshCombinedData(cloudSource.fetchTopPodcasts()),
        refreshCombinedData(cloudSource.fetchPodcasts())
    ) { newPodcasts, recommendPodcasts, topPodcasts, randomPodcasts ->
        val new = newPodcasts.getOrNull()
        val recommend = recommendPodcasts.getOrNull()
        val top = topPodcasts.getOrNull()
        val random = randomPodcasts.getOrNull()?.shuffled()?.take(10)
        if (new != null && recommend != null && top != null && random != null) {
            emit(Result.success(HomeScreenContent(new, recommend, top, random)))
        } else {
            emit(Result.failure(exceptionHandler.handle(EmptyDataException())))
        }
    }.flowOn(dispatcher)
}