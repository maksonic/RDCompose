package ru.maksonic.rdcompose.shared.ui_model.category.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author maksonic on 26.05.2022
 */
@Parcelize
data class CategoryUi(
    val id: Long? = null,
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String? = "",
) : Parcelable