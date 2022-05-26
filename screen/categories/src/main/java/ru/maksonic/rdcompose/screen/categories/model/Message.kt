package ru.maksonic.rdcompose.screen.categories.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.shared.ui_model.category.CategoryUi

/**
 * @Author maksonic on 26.05.2022
 */
@Immutable
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object FetchCategories : Ui()
        object RefreshCategories : Ui()
        data class OnCategoryClick(val categoryId: Long) : Ui()
    }

    sealed class Internal : Msg() {
        class FetchingSuccess(val categories: List<CategoryUi>) : Internal()
        class RefreshingSuccess(val categories: List<CategoryUi>) : Internal()
        class Error(val errorMessage: String? = "") : Internal()
    }
}