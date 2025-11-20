package me.partypronl.estateagent.domain.homes.source.funda

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import me.partypronl.estateagent.domain.homes.source.funda.data.FundaRepository
import me.partypronl.estateagent.domain.homes.source.model.ListingsSource
import org.koin.core.annotation.Factory

@Factory
class FundaSource(
    private val repository: FundaRepository,
) : ListingsSource {

    override suspend fun getListings(query: SearchQuery): List<Listing> {
        return repository.getListings(query)
    }
}
