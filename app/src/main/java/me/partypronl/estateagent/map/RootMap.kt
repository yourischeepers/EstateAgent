package me.partypronl.estateagent.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dev.chrisbanes.haze.HazeProgressive
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.partypronl.estateagent.BuildConfig
import me.partypronl.estateagent.presentation.root.map.RootMapNavigation
import me.partypronl.estateagent.presentation.root.map.RootMapViewModel
import me.partypronl.estateagent.presentation.root.map.controller.RootMapZoom
import me.partypronl.estateagent.presentation.root.map.model.RootMapHomeMarkerUiModel
import me.partypronl.estateagent.presentation.root.map.model.RootMapUiModel
import me.partypronl.estateagent.presentation.util.EventFlow
import me.partypronl.estateagent.util.CollectEvents
import org.koin.androidx.compose.koinViewModel

private object RootMapTokens {

    val TopBlurHeight = 32.dp
    val TopBlurRadius = 4.dp

    const val NetherlandsLat = 52.1326
    const val NetherlandsLon = 5.2913
}

@Composable
fun RootMap(
    modifier: Modifier = Modifier,
    viewModel: RootMapViewModel = koinViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(
            /* target = */ LatLng(RootMapTokens.NetherlandsLat, RootMapTokens.NetherlandsLon),
            /* zoom = */ RootMapZoom.Country.getGoogleZoom(),
            /* tilt = */ 0F,
            /* bearing = */ 0F,
        )
    }

    viewModel.navigation.HandleNavigation(cameraPositionState)

    Content(
        uiModel = uiModel,
        cameraPositionState = cameraPositionState,
        modifier = modifier
    )
}

@Composable
private fun Content(
    uiModel: RootMapUiModel,
    cameraPositionState: CameraPositionState,
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
) = Box(modifier = modifier) {
    Map(
        homeMarkers = uiModel.homeMarkers,
        cameraPositionState = cameraPositionState,
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(state = hazeState),
    )

    Box(
        modifier = Modifier
            .hazeEffect(state = hazeState) {
                blurRadius = RootMapTokens.TopBlurRadius
                progressive = HazeProgressive.verticalGradient(startIntensity = 1F, endIntensity = 0F)
            }
            .fillMaxWidth()
            .systemBarsPadding()
            .height(RootMapTokens.TopBlurHeight)
    )
}

@Composable
private fun Map(
    homeMarkers: List<RootMapHomeMarkerUiModel>,
    cameraPositionState: CameraPositionState,
    modifier: Modifier = Modifier,
) = GoogleMap(
    cameraPositionState = cameraPositionState,
    googleMapOptionsFactory = {
        GoogleMapOptions().mapId(BuildConfig.MAPS_STYLE_ID)
    },
    uiSettings = MapUiSettings(
        rotationGesturesEnabled = false,
        zoomControlsEnabled = false,
        myLocationButtonEnabled = false,
        compassEnabled = false,
        mapToolbarEnabled = false,
        tiltGesturesEnabled = false
    ),
    modifier = modifier,
) {
    homeMarkers.forEach { homeMarker ->
        val latLng = LatLng(homeMarker.lat, homeMarker.lon)

        MarkerComposable(
            state = rememberMarkerState(position = latLng),
            tag = homeMarker,
        ) {
            RootMapHomeMarker(homeMarker.state)
        }
    }
}

@Composable
private fun EventFlow<RootMapNavigation>.HandleNavigation(
    cameraPositionState: CameraPositionState,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    CollectEvents {
        when (it) {
            is RootMapNavigation.GoToLocation -> scope.launch {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(
                        /* latLng = */ LatLng(it.lat, it.lon),
                        /* zoom = */ it.zoom.getGoogleZoom(),
                    ),
                    durationMs = 2000,
                )
            }
        }
    }
}

private fun RootMapZoom.getGoogleZoom(): Float = when (this) {
    RootMapZoom.Country -> 8F
    RootMapZoom.City -> 13F
    RootMapZoom.Home -> 15F
}

