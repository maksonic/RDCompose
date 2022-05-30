package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi

/**
 * @Author maksonic on 29.05.2022
 */
@Immutable
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object RetryFetchPodcasts : Ui()
        object OnPodcastClicked : Ui()
    }

    sealed class Internal : Msg() {
        data class Success(val podcasts: List<PodcastUi>) : Internal()
        data class Error(val errorMsg: String? = "") : Internal()
        data class FetchTopBarTitle(val title: String) : Internal()
    }
}