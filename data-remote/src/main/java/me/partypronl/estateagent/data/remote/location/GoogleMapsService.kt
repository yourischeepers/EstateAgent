package me.partypronl.estateagent.data.remote.location

import io.ktor.client.call.body
import io.ktor.client.request.get
import me.partypronl.estateagent.data.remote.location.response.GeocodeResponse
import org.koin.core.annotation.Single

@Single
class GoogleMapsService(
    httpClientCreator: GoogleMapsHttpClientCreator,
    private val apiKeyProvider: GoogleMapsApiKeyProvider,
) {

    private val httpClient = httpClientCreator.createHttpClient()

    suspend fun geocode(address: String): GeocodeResponse {
        return httpClient
            .get("https://maps.googleapis.com/maps/api/geocode/json") {
                url {
                    parameters.append("address", address)
                    parameters.append("key", apiKeyProvider.apiKey)
                }
            }
            .body()
    }
}
