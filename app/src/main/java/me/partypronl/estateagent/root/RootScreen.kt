package me.partypronl.estateagent.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.partypronl.estateagent.map.RootMap
import me.partypronl.estateagent.navigation.RootNavHost
import me.partypronl.estateagent.navigation.Route
import me.partypronl.estateagent.navigation.rememberNavController

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    val navController = rememberNavController(Route.Home)

    RootMap(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
    )

    RootNavHost(navController = navController)
}
