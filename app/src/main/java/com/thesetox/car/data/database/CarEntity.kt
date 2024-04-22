package com.thesetox.car.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerPrice: Int,
    val make: String,
    val model: String,
    val rating: Int,
    @TypeConverters(ListConverter::class)
    val consList: List<String>,
    @TypeConverters(ListConverter::class)
    val prosList: List<String>,
) {
    companion object {
        private val listOfString = listOf("Something")

        fun Companion.listOfCarEntity(): List<CarEntity> {
            val landRover =
                CarEntity(
                    customerPrice = 120_000,
                    make = "Land Rover",
                    model = "Range Rover",
                    rating = 10,
                    consList = listOfString,
                    prosList = listOfString,
                )
            val roadster =
                CarEntity(
                    customerPrice = 220_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 7,
                    consList = listOfString,
                    prosList = listOfString,
                )
            val mercedes =
                CarEntity(
                    customerPrice = 95_000,
                    make = "Mercedes Benz",
                    model = "GLE coupe",
                    rating = 9,
                    consList = listOfString,
                    prosList = listOfString,
                )
            return listOf(landRover, roadster, mercedes)
        }
    }
}

class ListConverter {
    private val json = Json { encodeDefaults = true }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return json.decodeFromString(value)
    }
}
