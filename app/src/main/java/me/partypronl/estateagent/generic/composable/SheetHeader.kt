package me.partypronl.estateagent.generic.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PrimarySheetHeader(
    title: String,
    subtitle: String,
    @DrawableRes leadingIcon: Int? = null,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
) = SheetHeader(
    title = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    },
    subtitle = subtitle,
    actions = actions,
    modifier = modifier,
)

@Composable
fun SheetHeader(
    title: String,
    subtitle: String,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
) = SheetHeader(
    title = {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleSmall,
        )
    },
    subtitle = subtitle,
    actions = actions,
    modifier = modifier,
)

@Composable
fun SheetHeader(
    title: @Composable () -> Unit,
    subtitle: String,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
) {
    Column {
        title()

        Text(
            text = subtitle,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        actions()
    }
}
