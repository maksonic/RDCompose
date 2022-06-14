package ru.maksonic.rdcompose.data.podcasts.cache

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.data.podcasts.PodcastData
import javax.inject.Inject

/**
 * @Author maksonic on 11.06.2022
 */
class PodcastCacheToDataMapper @Inject constructor() : Mapper<PodcastCache, PodcastData> {
    override fun mapTo(o: PodcastData) =
        PodcastCache(
            o.id,
            o.categoryId,
            o.name,
            o.image,
            o.soundFile,
            o.isNew,
            o.isRecommend,
            o.isTop
        )

    override fun mapFrom(i: PodcastCache) =
        PodcastData(
            i.id,
            i.categoryId,
            i.name,
            i.image,
            i.soundFile,
            i.isNew,
            i.isRecommend,
            i.isTop
        )

}