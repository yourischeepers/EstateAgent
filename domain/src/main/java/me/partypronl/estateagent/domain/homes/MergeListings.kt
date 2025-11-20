package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.model.Listing
import org.koin.core.annotation.Factory

@Factory
class MergeListings {

    operator fun invoke(listings: List<Listing>): List<Listing> {
        // TODO
        // Look for duplicate IDs, and combine all properties
        return listings
    }
}
