package me.partypronl.estateagent.presentation.imagepreview.model

import org.koin.core.annotation.Factory

@Factory
class ImagePreviewUiMapper {

    fun toUiModel(
        imageUrls: List<String>,
        isAutoPlaying: Boolean,
        currentImageIndex: Int,
    ): ImagePreviewUiModel = ImagePreviewUiModel(
        currentImageUrl = imageUrls[currentImageIndex],
        totalImageCount = imageUrls.size,
        currentImageIndex = currentImageIndex,
        isAutoPlaying = isAutoPlaying,
    )
}
