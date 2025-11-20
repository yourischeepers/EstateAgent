package me.partypronl.estateagent.data.core.homes.source.funda

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery

interface FundaDataSource {

    suspend fun getListings(query: SearchQuery): List<Listing>
}
