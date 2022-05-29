package ru.maksonic.rdcompose.data.podcasts

import ru.maksonic.rdcompose.data.base.BaseCloudRepository
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloud
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastCloudToDataMapper
import ru.maksonic.rdcompose.data.podcasts.cloud.PodcastsCloudDataSource
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class PodcastsRepository @Inject constructor(
    cloudDataSource: PodcastsCloudDataSource,
    cloudMapper: PodcastCloudToDataMapper,
    dataToDomainMapper: PodcastDataToDomainMapper,
) : BaseCloudRepository<PodcastCloud, PodcastData, PodcastDomain>(
    baseCloudDataSource = cloudDataSource,
    cloudMapper = cloudMapper,
    dataToDomainMapper = dataToDomainMapper,
)