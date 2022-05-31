package ru.maksonic.rdcompose.feature.onboarding.update

import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.feature.onboarding.model.Cmd
import ru.maksonic.rdcompose.feature.onboarding.model.Model
import ru.maksonic.rdcompose.feature.onboarding.model.Msg
import ru.maksonic.rdcompose.feature.onboarding.program.OnboardingProgram
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    onboardingProgram: OnboardingProgram
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(),
    initialCmd = setOf(Cmd.FetchOnboardingList),
    subscriptions = listOf(onboardingProgram)
) {

    @OptIn(ExperimentalMaterialApi::class)
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.OnSkipOnboarding -> model to setOf(Cmd.NavigateToMainScreen)

            is Msg.Internal.OnboardingData -> {
                model.copy(onboardingList = msg.onboardingList) to emptySet()
            }
            is Msg.Ui.OnShowAuthSheet -> {
                model to setOf(Cmd.ShowAuthBottomSheet(msg.sheet, msg.scope))
            }

            is Msg.Ui.OnHideAuthSheet -> {
                model to setOf(Cmd.HideAuthBottomSheet(msg.sheet, msg.scope))
            }
            is Msg.Internal.SheetVisibility -> {
                model.copy(isShowAuthSheet = msg.isVisible) to emptySet()
            }
            is Msg.Ui.ShowPrivacy -> model to setOf(Cmd.NavigateToPrivacy)
            is Msg.Ui.ShowTermsOfUse -> model to setOf(Cmd.NavigateToTermsOfUse)
        }
}