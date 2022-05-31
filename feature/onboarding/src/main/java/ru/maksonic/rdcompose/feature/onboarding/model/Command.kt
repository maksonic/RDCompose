package ru.maksonic.rdcompose.feature.onboarding.model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope
import ru.maksonic.rdcompose.core.elm.Command

/**
 * @Author maksonic on 23.05.2022
 */
sealed class Cmd : Command {
    object FetchOnboardingList : Cmd()
    object NavigateToMainScreen : Cmd()

    @OptIn(ExperimentalMaterialApi::class)
    data class ShowAuthBottomSheet(
        val sheet: ModalBottomSheetState,
        val scope: CoroutineScope
    ) : Cmd()

    @OptIn(ExperimentalMaterialApi::class)
    data class HideAuthBottomSheet(
        val sheet: ModalBottomSheetState,
        val scope: CoroutineScope
    ) : Cmd()

    object NavigateToPrivacy : Cmd()
    object NavigateToTermsOfUse : Cmd()
}