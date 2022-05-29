package ru.maksonic.rdcompose.screen.podcast_list.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.StateModel

/**
 * @Author maksonic on 29.05.2022
 */
@Immutable
data class Model(
    val state: String
) : StateModel