package me.partypronl.estateagent.data.core.location.source

import me.partypronl.estateagent.domain.location.model.Location

interface LocationDataSource {

    suspend fun geocode(address: String): Location
}
