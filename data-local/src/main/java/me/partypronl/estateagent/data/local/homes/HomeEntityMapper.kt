package me.partypronl.estateagent.data.local.homes

import me.partypronl.estateagent.domain.homes.model.Home
import me.partypronl.estateagent.domain.homes.model.HomeDetails
import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.location.model.Location
import org.koin.core.annotation.Factory
import java.util.UUID

@Factory
class HomeEntityMapper {

    fun toEntities(models: List<Home>) = models.map(::toEntity)
    fun toModels(entities: List<HomeEntity>) = entities.map(::toModel)

    private fun toEntity(model: Home): HomeEntity {
        return HomeEntity(
            id = model.listing.id.toString(),
            state = model.state.ordinal,
            address = model.listing.address,
            postalCode = model.listing.postalCode,
            area = model.listing.area,
            imageUrls = model.listing.imageUrls.firstOrNull() ?: "",
            locationLat = model.details?.location?.lat,
            locationLon = model.details?.location?.lon,
        )
    }

    private fun toModel(entity: HomeEntity): Home {
        return Home(
            state = Home.State.entries[entity.state],
            listing = Listing(
                id = UUID.fromString(entity.id),
                address = entity.address,
                postalCode = entity.postalCode,
                area = entity.area,
                imageUrls = listOf(entity.imageUrls),
            ),
            details = if (entity.locationLat != null && entity.locationLon != null) {
                HomeDetails(
                    location = Location(
                        lat = entity.locationLat,
                        lon = entity.locationLon,
                    )
                )
            } else null,
        )
    }
}
