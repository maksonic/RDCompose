package ru.maksonic.rdcompose.domain.stories

/**
 * @Author maksonic on 07.06.2022
 */
data class AudioStoryDomain(
    val storyId: Long,
    val storyBackground: Int,
    val storySoundFile: String = "",
    val isFavorite: Boolean = false,
    val isViewed: Boolean = false
)