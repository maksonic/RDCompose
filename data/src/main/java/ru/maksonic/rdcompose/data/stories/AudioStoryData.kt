package ru.maksonic.rdcompose.data.stories

/**
 * @Author maksonic on 08.06.2022
 */
data class AudioStoryData(
    val storyId: Long,
    val storyBackground: String = "",
    val storySoundFile: String = "",
    val isFavorite: Boolean = false,
    val isViewed: Boolean = false
)