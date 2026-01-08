package me.partypronl.estateagent.presentation.root.map.controller

sealed interface RootMapAction {

    data class GoToLocation(
        val lat: Double,
        val lon: Double,
        val zoom: RootMapZoom,
    ) : RootMapAction
}
