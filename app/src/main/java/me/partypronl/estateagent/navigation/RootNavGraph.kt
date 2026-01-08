package me.partypronl.estateagent.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import me.partypronl.estateagent.detail.ListingDetailsScreen
import me.partypronl.estateagent.home.HomeScreen

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Home : Route

    @Serializable
    data class ListingDetails(val home: me.partypronl.estateagent.domain.homes.model.Home) : Route
}

fun EntryProviderScope<NavKey>.rootRoutes(navController: NavController) {
    this.entry<Route.Home> { HomeScreen() }
    this.entry<Route.ListingDetails> { ListingDetailsScreen(it.home, navController) }
}
