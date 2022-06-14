package ru.maksonic.rdcompose.data.podcasts

import android.util.Log
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCacheToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastsCacheDataSource
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloudToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import ru.maksonic.rdcompose.domain.podcasts.Podcasts
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
class PodcastRepositoryImpl @Inject constructor(
    private val cloudSource: PodcastsCloudDataSource,
    private val cacheSource: PodcastsCacheDataSource,
    private val cloudMapper: PodcastCloudToDataMapper,
    private val cacheMapper: PodcastCacheToDataMapper,
    private val domainMapper: PodcastDataToDomainMapper
) : PodcastsRepository {

    override fun fetchRecommendPodcasts(): Podcasts =
        cacheSource.readRecommendPodcasts().transform { cacheResponse ->
            cacheResponse.onSuccess { podcastsCache ->
                val podcastData = cacheMapper.mapFromList(podcastsCache)
                val podcasts = domainMapper.mapFromList(podcastData)
                Log.e("REPO", "FETCHED FROM CACHE ${podcasts.count()}")
                emit(Result.success(podcasts))
            }
            cacheResponse.onFailure {
                fetchAllPodcasts().collect { podcastsRequest ->
                    podcastsRequest.onSuccess { domainLists ->
                        val sss = domainLists.filter { it.isRecommend }
                        Log.e("REPO", "FETCHED FROM CLOUD ${domainLists.count()}")
                        emit(Result.success(sss))
                    }
                    podcastsRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override suspend fun fetchAllPodcasts(): Podcasts =
        cacheSource.readAllPodcasts().transform { cacheResponse ->
            cacheResponse.onSuccess { podcastsCache ->
                val podcastData = cacheMapper.mapFromList(podcastsCache)
                cacheSource.saveToCache(podcastData)
                val podcasts = domainMapper.mapFromList(podcastData)
                emit(Result.success(podcasts))
            }
            cacheResponse.onFailure {
                refreshAllPodcasts().collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

 override fun refreshAllPodcasts(): Podcasts =
        cloudSource.fetchAllPodcasts().transform { cloudResponse ->
            cloudResponse.onSuccess { podcastsCloud ->
                val podcastsData = cloudMapper.mapFromList(podcastsCloud)
                val podcasts = domainMapper.mapFromList(podcastsData)
                cacheSource.saveToCache(podcastsData)
                emit(Result.success(podcasts))
            }
            cloudResponse.onFailure { error -> emit(Result.failure(error)) }
        }


    override fun fetchPodcastByCategoryId(categoryId: String): Podcasts =
        cacheSource.readCachePodcastsByCategoryId(categoryId).transform { cacheResponse ->
            cacheResponse.onSuccess { podcastsCache ->
                val podcastsData = cacheMapper.mapFromList(podcastsCache)
                val podcasts = domainMapper.mapFromList(podcastsData)
                emit(Result.success(podcasts))
            }
            cacheResponse.onFailure {
                refreshPodcastByCategoryId(categoryId).collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override fun refreshPodcastByCategoryId(categoryId: String): Podcasts =
        cloudSource.fetchPodcastsByCategoryId(categoryId).transform { cloudResponse ->
            cloudResponse.onSuccess { podcastsCloud ->
                val podcastsData = cloudMapper.mapFromList(podcastsCloud)
                cacheSource.saveToCache(podcastsData)
                val podcasts = domainMapper.mapFromList(podcastsData)
                emit(Result.success(podcasts))
            }
            cloudResponse.onFailure { error -> emit(Result.failure(error)) }
        }
}