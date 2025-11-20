package me.partypronl.estateagent.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import me.partypronl.estateagent.home.HomeScreen

@Serializable
data object RootNavGraph {

    @Serializable
    data object Home
}

fun NavGraphBuilder.rootRoutes(
    navController: NavController,
) {
    composable<RootNavGraph.Home> {
        HomeScreen(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
