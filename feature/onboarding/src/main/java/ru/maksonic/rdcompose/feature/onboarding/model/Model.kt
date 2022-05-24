package ru.maksonic.rdcompose.feature.onboarding.model

import androidx.compose.runtime.Immutable
import ru.maksonic.rdcompose.core.elm.StateModel
import ru.maksonic.rdcompose.domain.onboarding.Onboarding

/**
 * @Author maksonic on 23.05.2022
 */
@Immutable
data class Model(
    val isShowAuthSheet: Boolean = false,
    val onboardingList: List<Onboarding> = emptyList()
) : StateModel
