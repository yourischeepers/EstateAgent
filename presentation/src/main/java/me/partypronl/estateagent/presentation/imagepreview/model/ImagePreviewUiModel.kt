package me.partypronl.estateagent.presentation.imagepreview.model

data class ImagePreviewUiModel(
    val currentImageUrl: String,
    val totalImageCount: Int,
    val currentImageIndex: Int,
    val isAutoPlaying: Boolean,
)
