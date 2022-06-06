package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.podcast.PodcastUi

/**
 * @Author maksonic on 29.05.2022
 */
@Stable
@Immutable
data class Model(
    val baseModel: BaseModel,
    val titleTopBar: String = "",
    val podcasts: List<PodcastUi> = emptyList(),
    val categoryInfo: CategoryInfo = CategoryInfo()
) : StateModel

@Stable
@Immutable
data class CategoryInfo(
    val name: String = "",
    val categoryImage: String? = ""
)