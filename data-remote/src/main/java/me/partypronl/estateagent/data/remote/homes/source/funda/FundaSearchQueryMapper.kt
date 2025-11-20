package me.partypronl.estateagent.data.remote.homes.source.funda

import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import org.koin.core.annotation.Factory

@Factory
class FundaSearchQueryMapper {

    // TODO actually fully use search query and implement all parameters

    fun toQueryString(searchQuery: SearchQuery): String {
        return "?selected_area=[%22${searchQuery.area.lowercase()}%22]&price=%220-1200%22&object_type=[%22apartment%22]&floor_area=%2240-%22"
    }
}
