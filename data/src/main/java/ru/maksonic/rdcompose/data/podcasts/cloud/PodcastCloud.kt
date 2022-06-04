package ru.maksonic.rdcompose.data.podcasts.cloud

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */

@Serializable
data class PodcastCloud(
    @SerialName("id") val id: Long = 0,
    @SerialName("name") val name: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("soundfile") val soundFile: String = ""
) : Abstract.CloudObject