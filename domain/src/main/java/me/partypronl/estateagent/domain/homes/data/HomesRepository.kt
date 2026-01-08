package me.partypronl.estateagent.domain.homes.data

import kotlinx.coroutines.flow.Flow
import me.partypronl.estateagent.domain.homes.model.Home

interface HomesRepository {

    fun observeHomes(): Flow<List<Home>>
    suspend fun saveHomes(homes: List<Home>)
}
