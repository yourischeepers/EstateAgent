package me.partypronl.estateagent.data.remote.homes.source.pararius

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.annotation.Single

@Single
class ParariusService(
    httpClientCreator: ParariusHttpClientCreator,
) {

    private val httpClient = httpClientCreator.createHttpClient()

    suspend fun getListingsHtml(
        cityName: String,
        propertyType: String,
        priceMin: Int,
        priceMax: Int,
        minSquareMeters: Int,
    ): String {
        val url = "https://www.pararius.com/apartments/${cityName.lowercase()}/${propertyType}/${priceMin}-${priceMax}/${minSquareMeters}m2"
        return httpClient.get(url).bodyAsText()
    }
}
