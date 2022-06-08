package ru.maksonic.rdcompose.feature.audio_story

/**
 * @Author maksonic on 07.06.2022
 */
data class AudioStoryUi(
    val storyId: Long,
    val storyBackground: Int,
    val storySoundFile: String = "",
    val isFavorite: Boolean = false,
    val isViewed: Boolean = false
)