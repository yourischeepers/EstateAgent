package me.partypronl.estateagent.presentation.detail.model

data class ListingDetailsUiModel(
    val address: String,
    val area: String,
    val rent: Int,
    val imageUrls: List<String>,
)
