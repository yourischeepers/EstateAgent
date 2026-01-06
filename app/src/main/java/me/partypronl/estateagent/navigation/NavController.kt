package me.partypronl.estateagent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

class NavController(val backStack: NavBackStack<NavKey>) {

    fun navigate(route: Route) {
        backStack.add(route)
    }

    fun popBackStack() {
        backStack.removeLastOrNull()
    }

    fun navigateWithoutBackStack(route: Route) {
        backStack.clear()
        backStack.add(route)
    }
}

@Composable
fun rememberNavController(startRoute: Route): NavController {
    val backStack = rememberNavBackStack(startRoute)
    return remember { NavController(backStack) }
}
