package me.partypronl.estateagent.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import me.partypronl.estateagent.domain.homes.ObserveNewHomes
import me.partypronl.estateagent.domain.homes.SyncListings
import me.partypronl.estateagent.presentation.home.model.HomeUiMapper
import me.partypronl.estateagent.presentation.home.model.HomeUiModel
import me.partypronl.estateagent.presentation.root.map.controller.RootMapController
import me.partypronl.estateagent.presentation.root.map.controller.RootMapZoom
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.TypedUiState
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import me.partypronl.estateagent.presentation.util.launchCatchingOnIO
import me.partypronl.estateagent.presentation.util.setError
import me.partypronl.estateagent.presentation.util.setNormal
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val syncListings: SyncListings,
    private val rootMapController: RootMapController,
    private val mapper: HomeUiMapper,
    private val observeNewHomes: ObserveNewHomes,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TypedUiState<HomeUiModel, Unit>>(TypedUiState.Loading)
    val uiState by lazy {
        startObservingNewHomes()
        _uiState.asStateFlow()
    }

    private val _events = MutableEventFlow<HomeEvent>()
    val events = _events.asEventFlow()

    private var hasOpenedMap = false

    fun onSyncClicked() = viewModelScope.launchCatchingOnIO {
        println("Sync!")
        syncListings()
    }

    fun onMapOpened() {
        if (!hasOpenedMap) {
            hasOpenedMap = true
            rootMapController.goToLocation(
                lat = 52.3676,
                lon = 4.9041, // TODO get these from use case
                zoomLevel = RootMapZoom.City,
            )
        }
    }

    fun onOpenMapClicked() {
        _events.emitEvent(HomeEvent.RevealMap)
    }

    fun onCloseMapClicked() {
        _events.emitEvent(HomeEvent.HideMap)
    }

    private fun startObservingNewHomes() = viewModelScope.launchCatchingOnIO(::onObserveNewHomesError) {
        observeNewHomes().collectLatest {
            _uiState.setNormal(
                mapper.toUiModel(it)
            )
        }
    }

    private fun onObserveNewHomesError(throwable: Throwable) {
        Napier.e("Failed to observe new homes", throwable)
        _uiState.setError()
    }
}
