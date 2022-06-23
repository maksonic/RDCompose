package ru.maksonic.rdcompose.domain.podcasts

import ru.maksonic.rdcompose.domain.base.Repository

/**
 * @Author maksonic on 13.06.2022
 */
interface PodcastsRepository : Repository<PodcastDomain> {
    suspend fun fetchPodcastByCategory(categoryId: Long): Podcasts
    suspend fun refreshPodcastByCategory(categoryId: Long): Podcasts
    suspend fun fetchHomeContent(): HomeContent
    suspend fun refreshHomeContent(): HomeContent
}