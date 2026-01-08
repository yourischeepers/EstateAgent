package me.partypronl.estateagent.data.remote.location

import me.partypronl.estateagent.data.core.location.source.LocationDataSource
import me.partypronl.estateagent.data.remote.location.response.GeocodeResponseMapper
import me.partypronl.estateagent.domain.location.model.Location
import org.koin.core.annotation.Factory

@Factory
class RemoteLocationDataSource(
    private val service: GoogleMapsService,
    private val responseMapper: GeocodeResponseMapper,
) : LocationDataSource {

    override suspend fun geocode(address: String): Location {
        val response = service.geocode(address)
        return responseMapper.toModel(response)
    }
}
