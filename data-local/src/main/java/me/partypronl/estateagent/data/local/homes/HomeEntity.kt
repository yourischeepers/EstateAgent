package me.partypronl.estateagent.data.local.homes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homes")
data class HomeEntity(
    @PrimaryKey val id: String,
    val state: Int,
    val address: String,
    val postalCode: String,
    val area: String,
    val imageUrls: String, // TODO make this a list
    val locationLat: Double?,
    val locationLon: Double?,
)
