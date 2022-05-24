package ru.maksonic.rdcompose.feature.onboarding.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Message
import ru.maksonic.rdcompose.domain.onboarding.Onboarding

/**
 * @Author maksonic on 23.05.2022
 */
@Immutable
sealed class Msg : Message {

    sealed class Ui : Msg() {
        object OnSkipOnboarding : Ui()
        @OptIn(ExperimentalMaterialApi::class)
        data class OnShowAuthSheet(
            val sheet: ModalBottomSheetState,
            val scope: CoroutineScope
        ) : Ui()
        @OptIn(ExperimentalMaterialApi::class)
        data class OnHideAuthSheet(
            val sheet: ModalBottomSheetState,
            val scope: CoroutineScope
        ) : Ui()

    }

    sealed class Internal : Msg() {
        data class OnboardingData(val onboardingList: List<Onboarding>) : Internal()
        data class SheetVisibility(val isVisible: Boolean) : Internal()
    }
}