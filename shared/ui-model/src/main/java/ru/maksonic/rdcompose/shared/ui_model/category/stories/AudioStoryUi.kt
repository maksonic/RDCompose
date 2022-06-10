package ru.maksonic.rdcompose.shared.ui_model.category.stories

/**
 * @Author maksonic on 07.06.2022
 */
data class AudioStoryUi(
    val storyId: Long = 0,
    val storyBackground: Int = 0,
    val storySoundFile: String = "",
    val isFavorite: Boolean = false,
    val isViewed: Boolean = false
)