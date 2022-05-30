package ru.maksonic.rdcompose.shared.ui_model.category.podcast

import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import javax.inject.Inject

/**
 * @Author maksonic on 30.05.2022
 */
class PodcastDomainToUiMapper @Inject constructor(): Mapper<PodcastDomain, PodcastUi> {
    override fun mapTo(o: PodcastUi) =
        PodcastDomain(o.id, o.categoryId, o.name, o.image, o.soundFile)

    override fun mapFrom(i: PodcastDomain) =
        PodcastUi(i.id, i.categoryId, i.name, i.image, i.soundFile)

}