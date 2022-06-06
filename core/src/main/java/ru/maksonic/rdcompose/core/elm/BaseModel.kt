package ru.maksonic.rdcompose.core.elm

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 06.06.2022
 */
/*
* Basic components of a feature state.
* Helps to avoid copy-paste when building a feature state.
* Add this class to the constructor of the feature state class.
* */
@Stable
@Immutable
data class BaseModel(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String = "",
): StateModel