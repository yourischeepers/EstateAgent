package  me.partypronl.estateagent.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.partypronl.estateagent.data.local.homes.HomeEntity
import me.partypronl.estateagent.data.local.homes.HomeQueries

@Database(
    entities = [
        HomeEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun homeQueries(): HomeQueries
}
