package ru.maksonic.rdcompose.screen.home.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi
import ru.maksonic.rdcompose.shared.ui_model.category.stories.AudioStoryUi

/**
 * @Author maksonic on 06.06.2022
 */
@Stable
@Immutable
data class Model(
    val baseModel: BaseModel,
    val story: Story = Story(),
    val content: HomeScreenContent = HomeScreenContent()
) : StateModel

@Stable
@Immutable
data class Story(
    val isFetched: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isViewedStory: Boolean = false,
    val isShowedStoryDialog: Boolean = false,
    val currentStory: Int = 0,
    val stories: List<AudioStoryUi> = emptyList(),
) : StateModel


@Stable
@Immutable
data class HomeScreenContent(
    val newPodcasts: List<PodcastUi> = emptyList(),
    val recommendPodcasts: List<PodcastUi> = emptyList(),
    val topPodcasts: List<PodcastUi> = emptyList(),
    val randomPodcasts: List<PodcastUi> = emptyList()
): StateModel