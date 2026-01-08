package me.partypronl.estateagent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun RootNavHost(
    navController: NavController,
    modifier: Modifier = Modifier,
) = NavDisplay(
    backStack = navController.backStack,
    onBack = { navController.popBackStack() },
    modifier = modifier,
    entryProvider = entryProvider {
        rootRoutes(navController)
    }
)
