package me.partypronl.estateagent.data.core.homes.source.funda

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import me.partypronl.estateagent.domain.homes.source.funda.data.FundaRepository
import org.koin.core.annotation.Factory

@Factory
class FundaRepositoryImpl(
    private val dataSource: FundaDataSource,
) : FundaRepository {

    override suspend fun getListings(query: SearchQuery): List<Listing> {
        return dataSource.getListings(query)
    }
}
