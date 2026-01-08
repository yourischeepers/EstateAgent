package me.partypronl.estateagent.presentation.root.map.model

import me.partypronl.estateagent.domain.homes.model.Home

data class RootMapUiModel(
    val homeMarkers: List<RootMapHomeMarkerUiModel> = emptyList(),
)

data class RootMapHomeMarkerUiModel(
    val lat: Double,
    val lon: Double,
    val state: RootMapHomeState,
    internal val home: Home,
)

enum class RootMapHomeState {

    AwaitingReaction,
    Reacted,
    Historic,
    Selected,
}
