package me.partypronl.estateagent.domain.homes.source.model

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery

interface ListingsSource {

    suspend fun getListings(query: SearchQuery): List<Listing>
}
