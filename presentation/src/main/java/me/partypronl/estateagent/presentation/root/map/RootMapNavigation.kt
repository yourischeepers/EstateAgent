package me.partypronl.estateagent.presentation.root.map

import me.partypronl.estateagent.presentation.root.map.controller.RootMapZoom

sealed interface RootMapNavigation {

    data class GoToLocation(
        val lat: Double,
        val lon: Double,
        val zoom: RootMapZoom,
    ) : RootMapNavigation
}
