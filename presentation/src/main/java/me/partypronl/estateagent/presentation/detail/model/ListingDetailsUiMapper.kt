package me.partypronl.estateagent.presentation.detail.model

import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class ListingDetailsUiMapper {

    fun toUiModel(home: Home): ListingDetailsUiModel = ListingDetailsUiModel(
        address = home.listing.address,
        area = home.listing.area,
        rent = 1289, // TODO implement
        imageUrls = home.listing.imageUrls + listOf(
            "https://cloud.funda.nl/valentina_media/223/183/386.jpg?options=width=2160",
            "https://cloud.funda.nl/valentina_media/223/183/378.jpg?options=width=2160",
        ), // TODO remove this
    )
}
