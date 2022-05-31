package ru.maksonic.rdcompose.feature.onboarding.program

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.elm.ElmProgram
import ru.maksonic.rdcompose.domain.onboarding.OnboardingRepository
import ru.maksonic.rdcompose.feature.onboarding.model.Cmd
import ru.maksonic.rdcompose.feature.onboarding.model.Msg
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 23.05.2022
 */
class OnboardingProgram @Inject constructor(
    private val repository: OnboardingRepository,
    private val navigator: GlobalNavigator
) : ElmProgram<Msg, Cmd> {
    override suspend fun execute(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchOnboardingList -> fetchOnboardingList(consumer)
            is Cmd.NavigateToMainScreen -> navigator.onboardingToMain()
            is Cmd.HideAuthBottomSheet -> hideSheet(consumer, cmd)
            is Cmd.ShowAuthBottomSheet -> showSheet(consumer, cmd)
        }
    }

    private fun fetchOnboardingList(consumer: (Msg) -> Unit) {
        val list = repository.fetchOnboardingList()
        consumer(Msg.Internal.OnboardingData(list))
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun showSheet(consumer: (Msg) -> Unit, cmd: Cmd.ShowAuthBottomSheet) {
        cmd.scope.launch { cmd.sheet.animateTo(ModalBottomSheetValue.Expanded) }
        consumer(Msg.Internal.SheetVisibility(true))
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun hideSheet(consumer: (Msg) -> Unit, cmd: Cmd.HideAuthBottomSheet) {
        cmd.scope.launch { cmd.sheet.animateTo(ModalBottomSheetValue.Hidden) }
        consumer(Msg.Internal.SheetVisibility(false))
    }
}