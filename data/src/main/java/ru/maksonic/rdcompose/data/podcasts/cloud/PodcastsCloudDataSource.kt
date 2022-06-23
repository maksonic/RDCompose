package ru.maksonic.rdcompose.data.podcasts.cloud

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.BaseCloudDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
typealias PodcastsCloud = Flow<Result<List<PodcastCloud>>>

interface PodcastsCloudDataSource {
    fun fetchPodcasts(): PodcastsCloud
    fun fetchNewPodcasts(): PodcastsCloud
    fun fetchRecommendPodcasts(): PodcastsCloud
    fun fetchTopPodcasts(): PodcastsCloud
    fun fetchPodcastsByCategoryId(categoryId: Long): PodcastsCloud

    class Base @Inject constructor(
        private val api: FirebaseApi,
        firestoreMapper: FirestorePodcastToCloudMapper,
        exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource.Base<PodcastCloud>(
        firestoreMapper, exceptionHandler, dispatcher
    ), PodcastsCloudDataSource {

        override fun fetchPodcasts(): PodcastsCloud = fetchCloudList { api.getPodcasts() }

        override fun fetchNewPodcasts(): PodcastsCloud = fetchCloudList { api.getNewPodcast() }

        override fun fetchRecommendPodcasts(): PodcastsCloud =
            fetchCloudList { api.getRecommendPodcast() }

        override fun fetchTopPodcasts(): PodcastsCloud = fetchCloudList { api.getTopPodcast() }

        override fun fetchPodcastsByCategoryId(categoryId: Long): PodcastsCloud =
            fetchCloudList { api.getPodcastsByCategory(categoryId) }
    }
}