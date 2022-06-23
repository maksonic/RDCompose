package ru.maksonic.rdcompose.data.podcasts

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
data class PodcastData(
    val id: Long = 0,
    val categoryId: Long = 0,
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    val isNew: Boolean = false,
    val isRecommend: Boolean = false,
    val isTop: Boolean = false,
) : Abstract.DataObject