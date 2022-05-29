package ru.maksonic.rdcompose.data.podcasts.cloud

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
data class PodcastCloud(
    val id: Long = 0,
    val categoryId: Long = 0,
    val name: String = "",
    val image: String = "",
    val soundFile: String = ""
) : Abstract.CloudObject