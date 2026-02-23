package me.partypronl.estateagent.generic.composable.amounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.partypronl.estateagent.R
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OverlayRentIndicator(
    rent: Int,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape = CircleShape,
        )
        .padding(
            vertical = 8.dp,
            horizontal = 12.dp,
        )
) {
    Text(
        text = stringResource(R.string.currency_symbol),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
    )

    Text(
        text = NumberFormat.getInstance(Locale.GERMANY).format(rent),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleSmall.copy(
            fontSize = 20.sp,
            lineHeight = 20.sp,
        ),
    )
}
