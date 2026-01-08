package me.partypronl.estateagent.data.core.location

import me.partypronl.estateagent.data.core.location.source.LocationDataSource
import me.partypronl.estateagent.domain.location.data.LocationRepository
import me.partypronl.estateagent.domain.location.model.Location
import org.koin.core.annotation.Factory

@Factory
class LocationRepositoryImpl(
    private val dataSource: LocationDataSource,
) : LocationRepository {

    override suspend fun geocode(address: String): Location {
        return dataSource.geocode(address)
    }
}
