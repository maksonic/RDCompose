package ru.maksonic.rdcompose.screen.home.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.feature.audio_story.AudioStoryUi

/**
 * @Author maksonic on 06.06.2022
 */
@Immutable
sealed class Msg : Message {

    sealed class Ui : Msg() {
        object Initial : Ui()
        object ShowStory : Ui()
        object CloseStory : Ui()
    }

    sealed class Internal : Msg() {
        data class StoriesSuccess(val stories: List<AudioStoryUi>) : Internal()
        data class StoriesError(val errorMsg: String) : Internal()
    }
}