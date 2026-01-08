package me.partypronl.estateagent.data.remote.location.response

import me.partypronl.estateagent.domain.location.model.Location
import org.koin.core.annotation.Factory

@Factory
class GeocodeResponseMapper {

    fun toModel(response: GeocodeResponse): Location {
        return response.results.firstOrNull()?.geometry?.location?.let {
            Location(it.lat, it.lng)
        } ?: throw Exception("No location found for address")
    }
}
