package me.partypronl.estateagent.domain.location.data

import me.partypronl.estateagent.domain.location.model.Location

interface LocationRepository {

    suspend fun geocode(address: String): Location
}
