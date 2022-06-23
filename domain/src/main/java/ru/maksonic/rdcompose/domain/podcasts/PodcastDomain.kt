package ru.maksonic.rdcompose.domain.podcasts

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
data class PodcastDomain(
    val id: Long = 0,
    val categoryId: Long = 0,
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    val isNew: Boolean = false,
    val isRecommend: Boolean = false,
    val isTop: Boolean = false,
) : Abstract.DomainObject

typealias Podcasts = Flow<Result<List<PodcastDomain>>>
typealias HomeContent = Flow<Result<HomeScreenContent>>

data class HomeScreenContent(
    val newPodcasts: List<PodcastDomain>,
    val recommendPodcasts: List<PodcastDomain>,
    val topPodcasts: List<PodcastDomain>,
    val randomPodcasts: List<PodcastDomain>,
)