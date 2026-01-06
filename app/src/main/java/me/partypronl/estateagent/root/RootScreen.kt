package me.partypronl.estateagent.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.partypronl.estateagent.map.RootMap
import me.partypronl.estateagent.navigation.RootNavHost

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    RootMap(modifier = Modifier.fillMaxSize())

//    RootNavHost(modifier = Modifier.fillMaxSize())
}
