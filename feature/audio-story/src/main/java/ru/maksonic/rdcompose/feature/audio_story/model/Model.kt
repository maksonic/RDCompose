package ru.maksonic.rdcompose.feature.audio_story.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.feature.audio_story.AudioStoryUi

/**
 * @Author maksonic on 07.06.2022
 */
@Stable
@Immutable
data class Model(
    val baseModel: BaseModel,
    val isFavorite: Boolean = false,
    val isViewed: Boolean = false,
    val storyState: StoryState = StoryState.COLLAPSED,
    val stories: List<AudioStoryUi> = emptyList(),
    val currentStory: Int = 0,
) : StateModel

enum class StoryState {
    EXPANDED, COLLAPSED
}