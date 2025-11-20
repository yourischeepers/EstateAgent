package me.partypronl.estateagent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) = NavHost(
    navController = navController,
    startDestination = RootNavGraph.Home,
    modifier = modifier,
) {
    rootRoutes(navController)
}
