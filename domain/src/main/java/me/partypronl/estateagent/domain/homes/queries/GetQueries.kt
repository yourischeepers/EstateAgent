package me.partypronl.estateagent.domain.homes.queries

import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import org.koin.core.annotation.Factory

@Factory
class GetQueries {

    operator fun invoke(): List<SearchQuery> {
        // TODO
        return listOf(
            SearchQuery(area = "Amsterdam")
        )
    }
}
