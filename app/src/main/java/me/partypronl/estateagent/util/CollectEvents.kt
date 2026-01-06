package me.partypronl.estateagent.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun <T> SharedFlow<T>.CollectEvents(
    onEvent: (T) -> Unit,
) {
    LaunchedEffect(Unit) {
        collect {
            onEvent(it)
        }
    }
}
