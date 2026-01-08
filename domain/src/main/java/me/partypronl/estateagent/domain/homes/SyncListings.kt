package me.partypronl.estateagent.domain.homes

import me.partypronl.estateagent.domain.homes.data.HomesRepository
import org.koin.core.annotation.Factory

@Factory
class SyncListings(
    private val getListings: GetListings,
    private val mergeListings: MergeListings,
    private val mergeIntoHomes: MergeIntoHomes,
    private val repository: HomesRepository,
    private val updateHomeWithDetails: UpdateHomeWithDetails,
) {

    suspend operator fun invoke() {
        val listings = getListings()
        val merged = mergeListings(listings)
        val homes = mergeIntoHomes(merged)
        val homesWithDetails = homes.map { updateHomeWithDetails(it) }

        repository.saveHomes(homesWithDetails)

        // TODO Status bar updates
    }
}
