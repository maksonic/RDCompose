package ru.maksonic.rdcompose.data.podcasts.cloud

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.data.podcasts.PodcastData
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class PodcastCloudToDataMapper @Inject constructor(): Mapper<PodcastCloud, PodcastData> {

    override fun mapTo(o: PodcastData) =
        PodcastCloud(o.id, o.categoryId, o.name, o.image, o.soundFile)

    override fun mapFrom(i: PodcastCloud) =
        PodcastData(i.id, i.categoryId, i.name, i.image, i.soundFile)
}