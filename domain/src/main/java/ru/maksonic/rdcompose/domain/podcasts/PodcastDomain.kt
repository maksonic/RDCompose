package ru.maksonic.rdcompose.domain.podcasts

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
data class PodcastDomain(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val soundFile: String = ""
) : Abstract.DomainObject