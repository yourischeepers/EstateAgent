package me.partypronl.estateagent.data.remote.homes.source.pararius

import me.partypronl.estateagent.data.remote.homes.source.exception.ExtractionException
import me.partypronl.estateagent.domain.homes.model.Listing
import org.jsoup.Jsoup
import org.koin.core.annotation.Factory
import java.util.UUID

@Factory
class ParariusHtmlExtractor {

    fun extractListings(html: String, area: String): List<Listing> {
        val document = Jsoup.parse(html)

        return document.select(ListingsSelector).map {
            val addressUnfiltered = it.selectFirst(ListingAddressSelector)?.text()
                ?: throw ExtractionException("Missing address")
            val postalCodeLineUnfiltered = it.selectFirst(ListingPostalCodeLineSelector)?.text()
                ?: throw ExtractionException("Missing postal code")
            val imageUrl = it.selectFirst(ListingCoverImageSelector)?.attr(ImageSourceUrlAttributeName)
                ?: throw ExtractionException("Missing image URL")

            val address = addressUnfiltered.replace(AddressLinePrefix, "")
            val postalCode = postalCodeLineUnfiltered.split(" ").subList(0, 2).joinToString("")

            Listing(
                id = UUID.randomUUID(),
                address = address,
                postalCode = postalCode,
                area = area,
                imageUrls = listOf(imageUrl),
            )
        }
    }

    companion object {

        const val ListingsSelector = "section.listing-search-item:not(.listing-search-item--project)"
        const val ListingAddressSelector = ".listing-search-item__link--title"
        const val ListingPostalCodeLineSelector = ".listing-search-item__sub-title"
        const val AddressLinePrefix = "Flat "
        const val ListingCoverImageSelector = ".picture__image"
        const val ImageSourceUrlAttributeName = "src"
    }
}
