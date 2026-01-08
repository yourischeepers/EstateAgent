package me.partypronl.estateagent.domain.homes.model

import kotlinx.serialization.Serializable
import me.partypronl.estateagent.domain.location.model.Location

@Serializable
data class HomeDetails(
    val location: Location,
)
