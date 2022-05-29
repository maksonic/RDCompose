package ru.maksonic.rdcompose.screen.podcast_list.update

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maksonic.rdcompose.core.common.KeyStore
import ru.maksonic.rdcompose.core.elm.ElmRuntime
import ru.maksonic.rdcompose.domain.podcasts.FetchPodcastsUseCase
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain
import ru.maksonic.rdcompose.navigation.api.navigator.MainNavigator
import ru.maksonic.rdcompose.screen.podcast_list.model.Cmd
import ru.maksonic.rdcompose.screen.podcast_list.model.Model
import ru.maksonic.rdcompose.screen.podcast_list.model.Msg
import javax.inject.Inject

/**
 * @Author maksonic on 28.05.2022
 */
typealias Update = Pair<Model, Set<Cmd>>

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    fetchPodcastsUseCase: FetchPodcastsUseCase,
    keyStore: KeyStore.NavigationKey,
    navigator: MainNavigator
) : ElmRuntime<Model, Msg, Cmd>(
    initialModel = Model(""),
    initialCmd = emptySet(),
    subscriptions = emptyList(),
    navigator = navigator
) {

    private var _list: MutableStateFlow<List<PodcastDomain>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<PodcastDomain>> = _list
    override fun update(msg: Msg, model: Model): Update =
        when (msg) {
            is Msg.Ui.OnPodcastClicked -> model to emptySet()
        }

    init {
        viewModelScope.launch {
            val categoryId = navigator.getArgument(keyStore.passedCategoryIdKey)
            Log.e("####", categoryId)
            fetchPodcastsUseCase(categoryId).collect { res ->
                res.onSuccess {
                    _list.value = it.sortedBy { podcastId -> podcastId.id }
                    Log.e("@@@", "${it.size}")

                }
                res.onFailure {
                    _list.value = emptyList()
                    Log.e("@@@", "UI FAIL!!!")

                }
            }
        }
    }
}
