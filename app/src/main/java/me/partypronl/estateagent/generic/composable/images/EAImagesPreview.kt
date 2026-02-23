package me.partypronl.estateagent.generic.composable.images

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition.Companion.None as EnterNone
import androidx.compose.animation.ExitTransition.Companion.None as ExitNone
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import me.partypronl.estateagent.generic.composable.amounts.OverlayRentIndicator
import me.partypronl.estateagent.presentation.imagepreview.ImagePreviewArgs
import me.partypronl.estateagent.presentation.imagepreview.ImagePreviewViewModel
import me.partypronl.estateagent.presentation.imagepreview.model.ImagePreviewUiModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private object EAImagesPreviewTokens {

    const val AspectRatio = 16 / 9F
}

@Composable
fun EAImagesPreview(
    rent: Int,
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
) = Box(
    modifier = modifier,
) {
    EAImagesPreview(
        imageUrls = imageUrls,
        modifier = Modifier.fillMaxWidth(),
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp),
    ) {
        OverlayRentIndicator(rent = rent)

        actions()
    }
}

@Composable
fun EAImagesPreview(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
    viewModel: ImagePreviewViewModel = koinViewModel {
        parametersOf(ImagePreviewArgs(imageUrls))
    },
) {
    val uiModel by viewModel.uiModel.collectAsState()

    Content(
        uiModel = uiModel,
        onClickNext = viewModel::onNextClicked,
        onClickPrevious = viewModel::onPreviousClicked,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(EAImagesPreviewTokens.AspectRatio)
    )
}

@Composable
private fun Content(
    uiModel: ImagePreviewUiModel,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    AnimatedCurrentImage(
        isAutoPlay = uiModel.isAutoPlaying,
        currentImageUrl = uiModel.currentImageUrl,
    )

    ProgressIndicators(
        totalImages = uiModel.totalImageCount,
        currentImage = uiModel.currentImageIndex,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(8.dp)
    )

    ClickAreas(
        onClickNext = onClickNext,
        onClickPrevious = onClickPrevious,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ClickAreas(
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier) {
    Box(
        modifier = Modifier
            .clickable(
                onClick = onClickPrevious,
                indication = null,
                interactionSource = null,
            )
            .weight(1F)
            .fillMaxHeight()
    )

    Spacer(modifier = Modifier.weight(1F))

    Box(
        modifier = Modifier
            .clickable(
                onClick = onClickNext,
                indication = null,
                interactionSource = null,
            )
            .weight(1F)
            .fillMaxHeight()
    )
}

@Composable
private fun ProgressIndicators(
    totalImages: Int,
    currentImage: Int,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
    for (i in 0 until totalImages) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(
                    color = if (currentImage >= i) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                .weight(1F)
                .height(4.dp)
        )
    }
}

@Composable
private fun AnimatedCurrentImage(
    isAutoPlay: Boolean,
    currentImageUrl: String,
    modifier: Modifier = Modifier,
) = AnimatedContent(
    targetState = currentImageUrl,
    modifier = modifier,
    transitionSpec = {
        if (isAutoPlay) {
            fadeIn() + slideInHorizontally { it / 8 } togetherWith fadeOut() + slideOutHorizontally { -it / 8 }
        } else {
            EnterNone togetherWith ExitNone
        }
    },
) { imageUrl ->
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
    )
}
