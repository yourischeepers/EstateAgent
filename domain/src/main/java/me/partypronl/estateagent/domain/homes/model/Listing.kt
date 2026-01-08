package me.partypronl.estateagent.domain.homes.model

import java.util.UUID

data class Listing(
    val id: UUID,
    val address: String,
    val postalCode: String,
    val area: String,
    val imageUrls: List<String>,
    // Address and everything
    // Address should probably act as an ID
    // Properties system, for easier merging
) {

    val effectiveId = "$address $postalCode"
}
