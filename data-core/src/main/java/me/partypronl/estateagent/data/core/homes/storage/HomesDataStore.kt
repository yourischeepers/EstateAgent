package me.partypronl.estateagent.data.core.homes.storage

import kotlinx.coroutines.flow.Flow
import me.partypronl.estateagent.domain.homes.model.Home

interface HomesDataStore {

    fun observe(): Flow<List<Home>>
    suspend fun save(homes: List<Home>)
}
