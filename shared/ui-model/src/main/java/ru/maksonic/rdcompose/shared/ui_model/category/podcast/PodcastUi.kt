package ru.maksonic.rdcompose.shared.ui_model.category.podcast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author maksonic on 30.05.2022
 */
@Parcelize
data class PodcastUi(
    val id: Long = 0,
    val categoryId: Long = 0,
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    val isNew: Boolean = false,
    val isRecommend: Boolean = false,
    val isTop: Boolean = false,
) : Parcelable