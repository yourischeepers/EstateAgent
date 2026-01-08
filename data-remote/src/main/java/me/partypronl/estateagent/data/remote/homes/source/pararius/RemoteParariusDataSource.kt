package me.partypronl.estateagent.data.remote.homes.source.pararius

import me.partypronl.estateagent.data.core.homes.source.pararius.ParariusDataSource
import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import org.koin.core.annotation.Factory

@Factory
class RemoteParariusDataSource(
    private val service: ParariusService,
    private val htmlExtractor: ParariusHtmlExtractor,
) : ParariusDataSource {


    override suspend fun getListings(query: SearchQuery): List<Listing> {
        val listingsHtml = service.getListingsHtml(
            cityName = query.area,
            propertyType = "apartment", // TODO This should probably come from an enum that is then mapped
            priceMin = 0,
            priceMax = 1250,
            minSquareMeters = 35,
        )

        return htmlExtractor.extractListings(listingsHtml, query.area)
    }
}
