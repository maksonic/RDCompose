package ru.maksonic.rdcompose.domain.podcasts.interactor

import ru.maksonic.rdcompose.domain.base.BaseInteractor
import ru.maksonic.rdcompose.domain.podcasts.HomeContent
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import javax.inject.Inject

/**
 * @Author maksonic on 21.06.2022
 */
class FetchHomeContentInteractor @Inject constructor(
    private val repository: PodcastsRepository
) : BaseInteractor<HomeContent, Nothing> {
    override suspend fun fetchData(id: Nothing?) = repository.fetchHomeContent()
    override suspend fun refreshData(id: Nothing?) = repository.refreshHomeContent()
}