package me.partypronl.estateagent.presentation.home

sealed interface HomeEvent {

    data object RevealMap : HomeEvent
    data object HideMap : HomeEvent
}
