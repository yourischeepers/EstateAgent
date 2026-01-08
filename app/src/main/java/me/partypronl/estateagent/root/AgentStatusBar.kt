package me.partypronl.estateagent.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import me.partypronl.estateagent.R

@Composable
fun AgentStatusBar(
    modifier: Modifier = Modifier,
    // TODO ViewModel to read UI state and stuff
) {

    Content(modifier = modifier)
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
) = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .padding(
            top = 12.dp,
            bottom = 8.dp,
        )
        .padding(horizontal = 24.dp,)
        .navigationBarsPadding()
        .fillMaxWidth(),
) {
    MainStatus()

    Details()
}

@Composable
private fun MainStatus(
    modifier: Modifier = Modifier,
) = Row(
    horizontalArrangement = Arrangement.spacedBy(2.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier,
) {
    Icon(
        painter = painterResource(R.drawable.ic_robot),
        contentDescription = "EstateAgent status",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(14.dp),
    )

    var dotCount by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            dotCount = (dotCount + 1) % 3
        }
    }

    Text(
        text = "Processing" + ".".repeat(dotCount + 1), // TODO
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
private fun Details(
    modifier: Modifier = Modifier,
) = Text(
    text = "Finding nearby facilities (58%)", // TODO
    color = MaterialTheme.colorScheme.onSurfaceVariant,
    style = MaterialTheme.typography.bodySmall,
    modifier = modifier,
)
