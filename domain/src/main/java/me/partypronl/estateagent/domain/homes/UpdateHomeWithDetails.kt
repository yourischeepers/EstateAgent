package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.model.Home
import me.partypronl.estateagent.domain.homes.model.HomeDetails
import me.partypronl.estateagent.domain.location.GeocodeAddress
import org.koin.core.annotation.Factory

@Factory
class UpdateHomeWithDetails(
    private val geocodeAddress: GeocodeAddress,
) {

    suspend operator fun invoke(home: Home): Home {
        return if (home.details != null) {
            home
        } else {
            home.copy(
                details = HomeDetails(
                    location = geocodeAddress(
                        address = home.listing.address,
                        postalCode = home.listing.postalCode,
                        area = home.listing.area,
                    )
                )
            )
        }
    }
}
