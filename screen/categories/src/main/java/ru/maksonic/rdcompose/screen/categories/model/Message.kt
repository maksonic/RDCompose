package ru.maksonic.rdcompose.screen.categories.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.shared.ui_model.category.category.CategoryUi

/**
 * @Author maksonic on 26.05.2022
 */
@Immutable
sealed class Msg : Message {
    sealed class Ui : Msg() {
        object FetchCategories : Ui()
        object RefreshCategories : Ui()
        data class OnCategoryClick(val categoryId: String, val categoryName: String) : Ui()
    }

    sealed class Internal : Msg() {
        class Success(val categories: List<CategoryUi>) : Internal()
        class Error(val errorMessage: String = "") : Internal()
    }
}