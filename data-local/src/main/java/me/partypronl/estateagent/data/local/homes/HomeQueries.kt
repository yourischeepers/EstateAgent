package me.partypronl.estateagent.data.local.homes

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeQueries {

    @Query("SELECT * FROM homes")
    fun selectAll(): Flow<List<HomeEntity>>

    @Upsert
    suspend fun insertOrUpdate(entity: HomeEntity)
}
