package ru.maksonic.rdcompose.data.podcasts

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class PodcastDataToDomainMapper @Inject constructor() : Mapper<PodcastData, PodcastDomain> {
    override fun mapTo(o: PodcastDomain) =
        PodcastData(o.id, o.categoryId, o.name, o.image, o.soundFile, o.isNew)

    override fun mapFrom(i: PodcastData) =
        PodcastDomain(i.id, i.categoryId, i.name, i.image, i.soundFile, i.isNew)

}