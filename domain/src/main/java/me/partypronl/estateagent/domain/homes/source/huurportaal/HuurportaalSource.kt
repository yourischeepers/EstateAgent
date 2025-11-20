package me.partypronl.estateagent.domain.homes.source.huurportaal

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import me.partypronl.estateagent.domain.homes.source.model.ListingsSource
import org.koin.core.annotation.Factory

@Factory
class HuurportaalSource : ListingsSource {

    override suspend fun getListings(query: SearchQuery): List<Listing> {
        // TODO
        return emptyList()
    }
}
