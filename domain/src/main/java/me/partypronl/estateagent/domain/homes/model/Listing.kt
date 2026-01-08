package me.partypronl.estateagent.domain.homes.model

import kotlinx.serialization.Serializable
import me.partypronl.estateagent.domain.util.UUIDSerializer
import java.util.UUID

@Serializable
data class Listing(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
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
