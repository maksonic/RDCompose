package ru.maksonic.rdcompose.screen.categories.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.shared.ui_model.category.CategoryUi

/**
 * @Author maksonic on 26.05.2022
 */
@Immutable
data class Model(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = "",
    val categories: List<CategoryUi> = emptyList()
) : StateModel