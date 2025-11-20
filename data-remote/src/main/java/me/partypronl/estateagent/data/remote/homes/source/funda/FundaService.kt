package me.partypronl.estateagent.data.remote.homes.source.funda

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.koin.core.annotation.Factory

@Factory
class FundaService(
    private val httpClient: HttpClient,
) {

    suspend fun getListingsHtml(queryString: String): String {
        return httpClient.get("https://www.funda.nl/zoeken/huur${queryString}").bodyAsText()
    }

    suspend fun getListingHtml(url: String): String {
        return httpClient.get(url).bodyAsText()
    }
}
