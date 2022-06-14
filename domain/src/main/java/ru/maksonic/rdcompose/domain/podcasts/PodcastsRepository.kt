package ru.maksonic.rdcompose.domain.podcasts

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 13.06.2022
 */
typealias Podcasts = Flow<Result<List<PodcastDomain>>>

interface PodcastsRepository {
    suspend fun fetchAllPodcasts(): Podcasts
    fun refreshAllPodcasts(): Podcasts
    fun fetchPodcastByCategoryId(categoryId: String): Podcasts
    fun refreshPodcastByCategoryId(categoryId: String): Podcasts
    fun fetchRecommendPodcasts(): Podcasts
}