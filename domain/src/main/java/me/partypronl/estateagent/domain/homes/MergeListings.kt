package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.model.Listing
import org.koin.core.annotation.Factory

@Factory
class MergeListings {

    operator fun invoke(listings: List<Listing>): List<Listing> {
        return listings
            .groupBy { it.effectiveId }
            .values
            .map { grouped ->
                grouped.reduce { acc, listing ->
                    invoke(acc, listing)
                }
            }
    }

    operator fun invoke(first: Listing, second: Listing): Listing {
        return Listing(
            id = first.id,
            address = first.address,
            postalCode = first.postalCode,
            area = first.area,
            imageUrls = (first.imageUrls + second.imageUrls).distinct(),
        )
    }
}
