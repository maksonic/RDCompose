package ru.maksonic.rdcompose.feature.privacy

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.navigation.api.navigator.GlobalNavigator
import javax.inject.Inject

/**
 * @Author maksonic on 31.05.2022
 */
@HiltViewModel
class PrivacyViewModel @Inject constructor(
    private val navigator: GlobalNavigator,
    private val keyStore: KeyStore.NavigationKey,
    private val rp: ResourceProvider,
) : ViewModel() {

    @Immutable
    data class PrivacyState(
        val title: String = "",
        val body: String = ""
    )

    private val _screenState: MutableStateFlow<PrivacyState> = MutableStateFlow(PrivacyState())
    val screenState = _screenState

    init {
        initContent(navigator.getStringArgument(keyStore.passedPrivacyKey))
    }

    private fun initContent(contentKey: String) {
        when (contentKey) {
            keyStore.privacyResult -> {
                _screenState.value = _screenState.value.copy(
                    title = rp.getString(R.string.scr_title_privacy_policy),
                    body = rp.getString(R.string.privacy_policy_content)
                )
            }
            keyStore.termsOfUseResult -> {
                _screenState.value = _screenState.value.copy(
                    title = rp.getString(R.string.scr_title_terms_of_use),
                    body = rp.getString(R.string.terms_of_use_content)
                )
            }
        }
    }

    fun backPressed() = navigator.backPressed()
}