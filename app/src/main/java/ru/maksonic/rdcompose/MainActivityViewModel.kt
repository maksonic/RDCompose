package ru.maksonic.rdcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.store.AppThemeSetting
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val themeSetting: AppThemeSetting,
) : ViewModel() {

    init {
        viewModelScope.launch {
            themeSetting.readTheme()
        }
    }
    val themeState = themeSetting.currentTheme
}