package me.partypronl.estateagent.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.partypronl.estateagent.domain.homes.SyncListings
import me.partypronl.estateagent.presentation.root.map.controller.RootMapController
import me.partypronl.estateagent.presentation.root.map.controller.RootMapZoom
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import me.partypronl.estateagent.presentation.util.launchCatchingOnIO
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val syncListings: SyncListings,
    private val rootMapController: RootMapController,
) : ViewModel() {

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
}
