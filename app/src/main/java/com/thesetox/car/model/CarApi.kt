package com.thesetox.car.model

import kotlinx.serialization.Serializable

@Serializable
data class CarApi(
    val customerPrice: Int,
    val make: String,
    val model: String,
    val rating: Int,
    val consList: List<String>,
    val prosList: List<String>,
) {
    companion object {
        private val listOfString = listOf("Something")

        fun Companion.listOfCarApi(): List<CarApi> {
            val landRover =
                CarApi(
                    customerPrice = 120_000,
                    make = "Land Rover",
                    model = "Range Rover",
                    rating = 10,
                    consList = listOfString,
                    prosList = listOfString,
                )
            val roadster =
                CarApi(
                    customerPrice = 220_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 7,
                    consList = listOfString,
                    prosList = listOfString,
                )
            val mercedes =
                CarApi(
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
