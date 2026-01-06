package me.partypronl.estateagent.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import me.partypronl.estateagent.home.HomeScreen

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Home : Route
}

fun EntryProviderScope<NavKey>.rootRoutes() {
    this.entry<Route.Home> { HomeScreen() }
}
