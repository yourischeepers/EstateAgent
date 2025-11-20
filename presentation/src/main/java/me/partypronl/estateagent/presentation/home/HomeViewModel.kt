package me.partypronl.estateagent.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.partypronl.estateagent.domain.homes.SyncListings
import me.partypronl.estateagent.presentation.util.launchCatchingOnIO
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val syncListings: SyncListings,
) : ViewModel() {

    fun onSyncClicked() = viewModelScope.launchCatchingOnIO {
        println("Sync!")
        syncListings()
    }
}
