package me.partypronl.estateagent.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
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

private val defaultTransitionMetadata = NavDisplay.transitionSpec {
    slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(500)
    ) togetherWith ExitTransition.KeepUntilTransitionsFinished
} + NavDisplay.popTransitionSpec {
    // Slide old content down, revealing the new content in place underneath
    EnterTransition.None togetherWith
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(500)
            )
} + NavDisplay.predictivePopTransitionSpec {
    // Slide old content down, revealing the new content in place underneath
    EnterTransition.None togetherWith
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(500)
            )
}

fun EntryProviderScope<NavKey>.rootRoutes(navController: NavController) {
    this.entry<Route.Home>(metadata = defaultTransitionMetadata) {
        HomeScreen()
    }

    this.entry<Route.ListingDetails>(metadata = defaultTransitionMetadata) {
        ListingDetailsScreen(
            home = it.home,
            navController = navController,
        )
    }
}
