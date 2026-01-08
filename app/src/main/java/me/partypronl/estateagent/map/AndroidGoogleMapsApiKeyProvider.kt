package me.partypronl.estateagent.map

import me.partypronl.estateagent.BuildConfig
import me.partypronl.estateagent.data.remote.location.GoogleMapsApiKeyProvider
import org.koin.core.annotation.Factory

@Factory
class AndroidGoogleMapsApiKeyProvider : GoogleMapsApiKeyProvider {

    override val apiKey: String
        get() = BuildConfig.MAPS_API_KEY
}
