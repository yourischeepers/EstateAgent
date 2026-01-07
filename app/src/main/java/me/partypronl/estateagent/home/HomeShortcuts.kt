package me.partypronl.estateagent.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.partypronl.estateagent.R

@Composable
fun HomeShortcuts(
    onClickOpenSearches: () -> Unit,
    onClickOpenTimeToReact: () -> Unit,
    onClickOpenWaitingForReaction: () -> Unit,
    onClickOpenHistory: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier.padding(vertical = 16.dp),
) {
    ShortCut(
        text = stringResource(R.string.home_shortcut_searches),
        icon = R.drawable.ic_search,
        onClick = onClickOpenSearches,
        modifier = Modifier.weight(1F),
    )

    ShortCut(
        text = stringResource(R.string.home_shortcut_react),
        icon = R.drawable.ic_react,
        onClick = onClickOpenTimeToReact,
        modifier = Modifier.weight(1F),
    )

    ShortCut(
        text = stringResource(R.string.home_shortcut_waiting),
        icon = R.drawable.ic_waiting,
        onClick = onClickOpenWaitingForReaction,
        modifier = Modifier.weight(1F),
    )

    ShortCut(
        text = stringResource(R.string.home_shortcut_history),
        icon = R.drawable.ic_history,
        onClick = onClickOpenHistory,
        modifier = Modifier.weight(1F),
    )
}

@Composable
private fun ShortCut(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick)
        .padding(vertical = 8.dp)
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = CircleShape,
            )
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
