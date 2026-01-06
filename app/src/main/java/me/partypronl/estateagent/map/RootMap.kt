package me.partypronl.estateagent.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.GoogleMapOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import dev.chrisbanes.haze.HazeProgressive
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import me.partypronl.estateagent.BuildConfig

private object RootMapTokens {

    val TopBlurHeight = 32.dp
    val TopBlurRadius = 4.dp
}

@Composable
fun RootMap(
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
    // TODO give this a ViewModel which handles search stuff, active filter and filter override for certain features
) = Box(modifier = modifier) {
    Map(
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
    modifier: Modifier = Modifier,
) {
    val cameraPositionState = rememberCameraPositionState()

    GoogleMap(
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
    )
}

