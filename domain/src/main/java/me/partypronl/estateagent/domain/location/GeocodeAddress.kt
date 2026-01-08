package me.partypronl.estateagent.domain.location

import me.partypronl.estateagent.domain.location.data.LocationRepository
import me.partypronl.estateagent.domain.location.model.Location
import org.koin.core.annotation.Factory

@Factory
class GeocodeAddress(
    private val repository: LocationRepository,
) {

    suspend operator fun invoke(
        address: String,
        postalCode: String,
        area: String,
    ): Location {
        return repository.geocode(
            "$address, $postalCode $area, $CountryName"
        )
    }

    companion object {

        const val CountryName = "Netherlands"
    }
}
