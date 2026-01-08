package me.partypronl.estateagent.presentation.root.map.model

import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class RootMapUiMapper {

    fun toUiModel(homes: List<Home>): RootMapUiModel {
        return RootMapUiModel(
            homeMarkers = homes
                .filter { it.details != null }
                .map {
                    RootMapHomeMarkerUiModel(
                        lat = it.details?.location?.lat ?: 0.0,
                        lon = it.details?.location?.lon ?: 0.0,
                        state = mapState(it.state),
                        home = it,
                    )
             }
        )
    }

    private fun mapState(state: Home.State): RootMapHomeState {
        return when (state) {
            Home.State.AwaitingRating -> RootMapHomeState.AwaitingReaction
            Home.State.Uninterested -> RootMapHomeState.Historic
            Home.State.Interested -> RootMapHomeState.AwaitingReaction
            Home.State.Reacted -> RootMapHomeState.Reacted
            Home.State.Rejected -> RootMapHomeState.Historic
            Home.State.Accepted -> RootMapHomeState.Historic
        }
    }
}
