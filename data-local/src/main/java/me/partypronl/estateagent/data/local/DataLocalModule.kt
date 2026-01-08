package me.partypronl.estateagent.data.local

import android.content.Context
import androidx.room.Room
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("me.partypronl.estateagent.data.local")
class DataLocalModule {

    @Single
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "database",
            )
            .build()
    }
}
