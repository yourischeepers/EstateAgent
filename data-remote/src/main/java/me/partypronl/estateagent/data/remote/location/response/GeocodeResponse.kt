package me.partypronl.estateagent.data.remote.location.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodeResponse(

    @SerialName("results")
    val results: List<GeocodeResultResponse>,
)

@Serializable
data class GeocodeResultResponse(

    @SerialName("geometry")
    val geometry: GeocodeGeometryResponse,
)

@Serializable
data class GeocodeGeometryResponse(

    @SerialName("location")
    val location: GeocodeLocationResponse,
)

@Serializable
data class GeocodeLocationResponse(

    @SerialName("lat")
    val lat: Double,

    @SerialName("lng")
    val lng: Double,
)
