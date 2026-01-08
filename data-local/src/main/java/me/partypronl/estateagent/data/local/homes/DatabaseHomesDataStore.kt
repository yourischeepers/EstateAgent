package me.partypronl.estateagent.data.local.homes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.partypronl.estateagent.data.core.homes.storage.HomesDataStore
import me.partypronl.estateagent.data.local.AppDatabase
import me.partypronl.estateagent.domain.homes.model.Home
import org.koin.core.annotation.Factory

@Factory
class DatabaseHomesDataStore(
    private val appDatabase: AppDatabase,
    private val mapper: HomeEntityMapper,
) : HomesDataStore {

    override fun observe(): Flow<List<Home>> {
        return appDatabase
            .homeQueries()
            .selectAll()
            .map { mapper.toModels(it) }
    }

    override suspend fun save(homes: List<Home>) {
        val mapped = mapper.toEntities(homes)

        for (entity in mapped) {
            appDatabase
                .homeQueries()
                .insertOrUpdate(entity)
        }
    }
}
