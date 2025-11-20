package me.partypronl.estateagent.domain.homes.model

import java.util.UUID

data class Listing(
    val id: UUID,
    val name: String,
    // Address and everything
    // Address should probably act as an ID
    // Properties system, for easier merging
)
