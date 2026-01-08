package me.partypronl.estateagent.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import me.partypronl.estateagent.R

@Composable
fun HomeNewListingsSection(
    imageUrls: List<String>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.height(136.dp),
    ) {
        imageUrls.getOrNull(0)?.let {
            HomePreviewCard(
                imageUrl = it,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight(),
            )
        }

        imageUrls.getOrNull(1)?.let {
            HomePreviewCard(
                imageUrl = it,
                modifier = Modifier
                    .weight(weight = if (imageUrls.size == 2) 1F else 0.5F)
                    .fillMaxHeight(),
            )
        }

        imageUrls.getOrNull(2)?.let {
            HomePreviewCard(
                imageUrl = it,
                modifier = Modifier
                    .weight(0.5F)
                    .fillMaxHeight(),
            )
        }
    }

    Box(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = CircleShape,
            )
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_play),
            contentDescription = stringResource(R.string.home_new_listings_start_alt),
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun HomePreviewCard(
    imageUrl: String,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}
