package me.partypronl.estateagent.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.estateagent.R
import me.partypronl.estateagent.presentation.root.map.model.RootMapHomeState

@Composable
fun RootMapHomeMarker(
    state: RootMapHomeState,
    modifier: Modifier = Modifier,
) = Box(
    contentAlignment = Alignment.TopCenter,
    modifier = modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_marker_background),
        contentDescription = null,
        tint = state.getMarkerBackgroundColor(),
    )

    Icon(
        painter = painterResource(R.drawable.ic_marker_foreground),
        contentDescription = null,
        tint = state.getMarkerForegroundColor(),
        modifier = Modifier
            .padding(top = 3.dp)
            .size(16.dp)
    )
}

@Composable
private fun RootMapHomeState.getMarkerBackgroundColor(): Color {
    return when (this) {
        RootMapHomeState.AwaitingReaction -> MaterialTheme.colorScheme.primary
        RootMapHomeState.Reacted -> MaterialTheme.colorScheme.onSurface
        RootMapHomeState.Historic -> MaterialTheme.colorScheme.surfaceContainerLow
        RootMapHomeState.Selected -> MaterialTheme.colorScheme.secondary
    }
}

@Composable
private fun RootMapHomeState.getMarkerForegroundColor(): Color {
    return when (this) {
        RootMapHomeState.Historic -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.surfaceContainerLowest
    }
}
