package com.thesetox.car.model

import kotlinx.serialization.Serializable

@Serializable
data class CarApi(
    val customerPrice: Int,
    val make: String,
    val model: String,
    val rating: Int,
) {
    companion object {
        fun Companion.listOfCarApi(): List<CarApi> {
            val landRover =
                CarApi(
                    customerPrice = 120_000,
                    make = "Land Rover",
                    model = "Range Rover",
                    rating = 10,
                )
            val roadster =
                CarApi(
                    customerPrice = 220_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 7,
                )
            val mercedes =
                CarApi(
                    customerPrice = 95_000,
                    make = "Mercedes Benz",
                    model = "GLE coupe",
                    rating = 9,
                )
            return listOf(landRover, roadster, mercedes)
        }
    }
}
