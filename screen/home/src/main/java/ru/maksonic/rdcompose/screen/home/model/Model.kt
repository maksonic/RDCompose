package ru.maksonic.rdcompose.screen.home.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.stories.AudioStoryUi

/**
 * @Author maksonic on 06.06.2022
 */
@Stable
@Immutable
data class Model(
    val baseModel: BaseModel = BaseModel(),
    val story: Story = Story()
) : StateModel

@Stable
@Immutable
data class Story(
    val isViewedStory: Boolean = false,
    val isShowedStoryDialog: Boolean = false,
    val currentStory: Int = 0,
    val stories: List<AudioStoryUi> = emptyList(),
) : StateModel