package me.partypronl.estateagent.data.remote.homes.source.funda

import me.partypronl.estateagent.data.core.homes.source.funda.FundaDataSource
import me.partypronl.estateagent.domain.homes.model.Listing
import me.partypronl.estateagent.domain.homes.queries.model.SearchQuery
import org.koin.core.annotation.Factory

@Factory
class RemoteFundaDataSource(
    private val searchQueryMapper: FundaSearchQueryMapper,
    private val service: FundaService,
    private val htmlExtractor: FundaHtmlExtractor,
) : FundaDataSource {

    override suspend fun getListings(query: SearchQuery): List<Listing> {
        val queryString = searchQueryMapper.toQueryString(query)
        val html = service.getListingsHtml(queryString)

        val listingUrls = htmlExtractor.extractUrls(html)
        val listings = listingUrls.map {
            val listingHtml = service.getListingHtml(it)
            htmlExtractor.extractListing(listingHtml)
        }
        listings.forEach {
            println("Found listing with name ${it.name}")
        }

        return emptyList()
    }
}
