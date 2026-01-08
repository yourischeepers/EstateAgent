package me.partypronl.estateagent.presentation.home.model

import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class HomeUiMapper {

    fun toUiModel(newHomes: List<Home>): HomeUiModel {
        return HomeUiModel(
            newHomesAmount = newHomes.size,
            newHomesImages = newHomes.mapNotNull { it.listing.imageUrls.firstOrNull() },
        )
    }
}
