package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.GetQueries
import me.partypronl.estateagent.domain.homes.source.GetSources
import org.koin.core.annotation.Factory

@Factory
class GetListings(
    private val getSources: GetSources,
    private val getQueries: GetQueries,
) {

    suspend operator fun invoke(): List<Listing> {
        val sources = getSources()
        val queries = getQueries()

        return sources.flatMap { source ->
            queries.flatMap { query ->
                source.getListings(query)
            }
        }
    }
}
