package me.partypronl.estateagent.presentation.root.map.controller

import org.koin.core.annotation.Factory

@Factory
class RootMapController(
    private val actionHolder: RootMapControllerActionHolder,
) {

    fun goToLocation(lat: Double, lon: Double, zoomLevel: RootMapZoom) {
        actionHolder.sendAction(
            action = RootMapAction.GoToLocation(
                lat = lat,
                lon = lon,
                zoom = zoomLevel,
            )
        )
    }
}
