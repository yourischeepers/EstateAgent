package me.partypronl.estateagent.domain.homes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.partypronl.estateagent.domain.homes.data.HomesRepository
import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class ObserveNewHomes(
    private val repository: HomesRepository,
) {

    operator fun invoke(): Flow<List<Home>> {
        return repository
            .observeHomes()
            .map { homes ->
                homes.filter { it.state == Home.State.AwaitingRating }
            }
    }
}
