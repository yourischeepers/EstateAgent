package me.partypronl.estateagent.domain.homes

import org.koin.core.annotation.Factory

@Factory
class SyncListings(
    private val getListings: GetListings,
    private val mergeListings: MergeListings,
) {

    suspend operator fun invoke() {
        val listings = getListings()
        val merged = mergeListings(listings)

        // TODO save new ones to database, send notification

        merged.forEach {
            println("Found home ${it.name}")
        }
    }
}
