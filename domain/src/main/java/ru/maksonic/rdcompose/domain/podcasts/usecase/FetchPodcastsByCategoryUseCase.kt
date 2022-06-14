package ru.maksonic.rdcompose.domain.podcasts.usecase

import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.podcasts.Podcasts
import ru.maksonic.rdcompose.domain.podcasts.PodcastsRepository
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class FetchPodcastsByCategoryUseCase @Inject constructor(
    private val repository: PodcastsRepository
) : BaseUseCase<Podcasts, String> {
    override suspend fun invoke(args: String?): Podcasts =
        repository.fetchPodcastByCategoryId(args ?: "")
}
