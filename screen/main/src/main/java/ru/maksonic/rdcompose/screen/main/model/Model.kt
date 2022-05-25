package ru.maksonic.rdcompose.screen.main.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.StateModel

/**
 * @Author maksonic on 25.05.2022
 */
@Immutable
data class Model(val s: String) : StateModel