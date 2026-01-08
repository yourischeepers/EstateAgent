package me.partypronl.estateagent.domain.homes

import kotlinx.coroutines.flow.Flow
import me.partypronl.estateagent.domain.homes.data.HomesRepository
import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class ObserveHomes(
    private val repository: HomesRepository,
) {

    operator fun invoke(): Flow<List<Home>> {
        return repository.observeHomes()
    }
}
