package ru.maksonic.rdcompose.screen.categories.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.rdcompose.core.elm.BaseModel
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryUi

/**
 * @Author maksonic on 26.05.2022
 */
@Stable
@Immutable
data class Model(
    val baseModel: BaseModel,
    val categories: List<CategoryUi> = emptyList()
) : StateModel