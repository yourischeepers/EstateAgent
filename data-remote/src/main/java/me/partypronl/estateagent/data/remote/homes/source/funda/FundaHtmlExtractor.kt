package me.partypronl.estateagent.data.remote.homes.source.funda

import me.partypronl.estateagent.data.remote.homes.source.exception.ExtractionException
import me.partypronl.estateagent.domain.homes.model.Listing
import org.jsoup.Jsoup
import org.koin.core.annotation.Factory
import java.util.UUID

@Factory
class FundaHtmlExtractor {

    // TODO Probably want some nice utilities to do this kind of extraction faster

    fun extractListing(html: String): Listing {
        val document = Jsoup.parse(html)

        return Listing(
            id = UUID.randomUUID(),
            name = document.selectFirst(AddressSelector)?.text() ?: throw ExtractionException("Missing address")
        )
    }

    fun extractUrls(html: String): List<String> {
        val document = Jsoup.parse(html)

        return document.select(UrlElementSelector)
            .map {
                BaseUrl + it.attr("href")
            }
    }

    companion object {

        private const val BaseUrl = "https://www.funda.nl"
        private const val UrlElementSelector = "h2 > a"
        private const val AddressSelector = "span.block.text-2xl.font-bold"
    }
}
