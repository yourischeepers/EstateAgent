package me.partypronl.estateagent.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.partypronl.estateagent.domain.homes.SyncListings
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import me.partypronl.estateagent.presentation.util.launchCatchingOnIO
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val syncListings: SyncListings,
) : ViewModel() {

    private val _events = MutableEventFlow<HomeEvent>()
    val events = _events.asEventFlow()

    fun onSyncClicked() = viewModelScope.launchCatchingOnIO {
        println("Sync!")
        syncListings()
    }

    fun onOpenMapClicked() {
        _events.emitEvent(HomeEvent.RevealMap)
    }

    fun onCloseMapClicked() {
        _events.emitEvent(HomeEvent.HideMap)
    }
}
