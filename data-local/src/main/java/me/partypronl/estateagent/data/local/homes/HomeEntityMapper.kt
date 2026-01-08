package me.partypronl.estateagent.data.local.homes

import me.partypronl.estateagent.domain.homes.model.Home
import me.partypronl.estateagent.domain.homes.model.Listing
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
            imageUrls = model.listing.imageUrls.firstOrNull() ?: "",
        )
    }

    private fun toModel(entity: HomeEntity): Home {
        return Home(
            state = Home.State.entries[entity.state],
            listing = Listing(
                id = UUID.fromString(entity.id),
                address = entity.address,
                postalCode = entity.postalCode,
                imageUrls = listOf(entity.imageUrls),
            ),
        )
    }
}
