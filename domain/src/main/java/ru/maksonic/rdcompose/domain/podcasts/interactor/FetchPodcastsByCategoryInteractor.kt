package ru.maksonic.rdcompose.domain.podcasts.interactor

import ru.maksonic.rdcompose.domain.base.BaseInteractor
import ru.maksonic.rdcompose.domain.podcasts.Podcasts
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
class FetchPodcastsByCategoryInteractor @Inject constructor(
    private val repository: PodcastsRepository
) : BaseInteractor<Podcasts, Long> {
    override suspend fun fetchData(id: Long?): Podcasts =
        repository.fetchPodcastByCategory(id ?: 0)

    override suspend fun refreshData(id: Long?): Podcasts =
        repository.refreshPodcastByCategory(id ?: 0)
}