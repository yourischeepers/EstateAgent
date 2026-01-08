package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.data.HomesRepository
import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class SyncListings(
    private val getListings: GetListings,
    private val mergeListings: MergeListings,
    private val repository: HomesRepository,
) {

    suspend operator fun invoke() {
        val listings = getListings()
        val merged = mergeListings(listings)

        val homes = merged.map {
            Home(
                listing = it,
                state = Home.State.AwaitingRating,
            )
        }

        repository.saveHomes(homes)

        // TODO send notification

        merged.forEach {
            println("Found home ${it.address} ${it.postalCode}")
        }
    }
}
