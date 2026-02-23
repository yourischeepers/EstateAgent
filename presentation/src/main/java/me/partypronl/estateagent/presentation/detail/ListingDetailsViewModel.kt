package me.partypronl.estateagent.presentation.detail

import androidx.lifecycle.ViewModel
import me.partypronl.estateagent.presentation.root.map.controller.RootMapController
import me.partypronl.estateagent.presentation.root.map.controller.RootMapZoom
import me.partypronl.estateagent.presentation.util.MutableEventFlow
import me.partypronl.estateagent.presentation.util.asEventFlow
import me.partypronl.estateagent.presentation.util.emitEvent
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class ListingDetailsViewModel(
    @InjectedParam private val args: ListingDetailsArgs,
    private val rootMapController: RootMapController,
) : ViewModel() {

    private val _navigation = MutableEventFlow<ListingDetailsNavigation>()
    val navigation = _navigation.asEventFlow()

    init {
        moveMapToHome()
    }

    fun onBackClicked() {
        _navigation.emitEvent(ListingDetailsNavigation.GoBack)
    }

    private fun moveMapToHome() {
        args.home.details?.let {
            rootMapController.goToLocation(
                lat = it.location.lat + LatOffset,
                lon = it.location.lon,
                zoomLevel = RootMapZoom.Home,
            )
        }
    }

    companion object {

        const val LatOffset = -0.008
    }
}
