package me.partypronl.estateagent.domain.homes.source.funda.data

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery

interface FundaRepository {

    suspend fun getListings(query: SearchQuery): List<Listing>
}
