package ru.maksonic.rdcompose.domain.podcasts

import ru.maksonic.rdcompose.domain.base.BaseUseCase
import ru.maksonic.rdcompose.domain.base.CloudRepository
import ru.maksonic.rdcompose.domain.base.Podcasts
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class FetchPodcastsUseCase @Inject constructor(
    private val repository: CloudRepository<PodcastDomain>
) : BaseUseCase<Podcasts, String?> {

    override suspend fun invoke(args: String?) = repository.fetchCloudDataList(args.toString())
}
