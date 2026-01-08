package me.partypronl.estateagent.data.core.homes

import kotlinx.coroutines.flow.Flow
import me.partypronl.estateagent.data.core.homes.storage.HomesDataStore
import me.partypronl.estateagent.domain.homes.data.HomesRepository
import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class HomesRepositoryImpl(
    private val dataStore: HomesDataStore,
) : HomesRepository {

    override fun observeHomes(): Flow<List<Home>> {
        return dataStore.observe()
    }

    override suspend fun saveHomes(homes: List<Home>) {
        dataStore.save(homes)
    }
}
