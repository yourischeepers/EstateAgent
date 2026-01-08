package me.partypronl.estateagent.presentation.root.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import me.partypronl.estateagent.domain.homes.ObserveHomes
import me.partypronl.estateagent.presentation.root.map.controller.RootMapAction
import me.partypronl.estateagent.presentation.root.map.controller.RootMapControllerActionHolder
import me.partypronl.estateagent.presentation.root.map.model.RootMapUiMapper
import me.partypronl.estateagent.presentation.root.map.model.RootMapUiModel
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import me.partypronl.estateagent.presentation.util.launchCatching
import me.partypronl.estateagent.presentation.util.launchCatchingOnIO
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RootMapViewModel(
    private val actionHolder: RootMapControllerActionHolder,
    private val observeHomes: ObserveHomes,
    private val mapper: RootMapUiMapper,
) : ViewModel() {

    private val _uiModel = MutableStateFlow(RootMapUiModel())
    val uiModel by lazy {
        startObservingHomes()
        _uiModel.asStateFlow()
    }

    // TODO Later this should hold stuff to help show certain stuff on the map, and handle filtering

    private val _navigation = MutableEventFlow<RootMapNavigation>()
    val navigation = _navigation.asEventFlow()

    init {
        startObservingActions()
    }

    private fun handleAction(action: RootMapAction) {
        when (action) {
            is RootMapAction.GoToLocation -> handleGoToLocation(action)
        }
    }

    private fun handleGoToLocation(action: RootMapAction.GoToLocation) {
        _navigation.emitEvent(
            RootMapNavigation.GoToLocation(
                lat = action.lat,
                lon = action.lon,
                zoom = action.zoom,
            )
        )
    }

    private fun startObservingActions() = viewModelScope.launchCatching {
        actionHolder.actions.collect {
            handleAction(it)
        }
    }

    // TODO Later this should take into account active filter applied by given screen
    private fun startObservingHomes() = viewModelScope.launchCatchingOnIO {
        observeHomes().collectLatest {
            _uiModel.value = mapper.toUiModel(it)
        }
    }
}
