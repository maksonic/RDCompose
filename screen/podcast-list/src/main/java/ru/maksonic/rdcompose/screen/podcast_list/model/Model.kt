package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi

/**
 * @Author maksonic on 29.05.2022
 */
@Immutable
data class Model(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val titleTopBar: String = "",
    val podcasts: List<PodcastUi> = emptyList(),
    val errorMsg: String = "",
    val categoryInfo: CategoryInfo = CategoryInfo()
) : StateModel

@Immutable
data class CategoryInfo(
    val name: String = "",
    val categoryImage: String? = ""
)