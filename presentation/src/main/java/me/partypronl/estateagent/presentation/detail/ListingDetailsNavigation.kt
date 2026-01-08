package me.partypronl.estateagent.presentation.detail

sealed interface ListingDetailsNavigation {

    data object GoBack : ListingDetailsNavigation
}
