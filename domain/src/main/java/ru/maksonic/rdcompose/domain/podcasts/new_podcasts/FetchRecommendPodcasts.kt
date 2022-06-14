package ru.maksonic.rdcompose.domain.podcasts.new_podcasts

import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.podcasts.Podcasts
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import javax.inject.Inject

/**
 * @Author maksonic on 11.06.2022
 */
class FetchRecommendPodcasts @Inject constructor(
    private val repository: PodcastsRepository
) : BaseUseCase<Podcasts, Nothing> {
    override suspend fun invoke(args: Nothing?): Podcasts = repository.fetchRecommendPodcasts()
}