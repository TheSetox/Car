package com.thesetox.car.model

import com.thesetox.car.R

data class Car(
    val name: String = "",
    val image: Int = 0,
    val rating: Int = 0,
    val price: String = "0",
    val consList: List<String> = emptyList(),
    val prosList: List<String> = emptyList(),
) {
    companion object {
        fun Companion.listOfString() = listOf("Something", "Other", "Another Item")

        fun Companion.carPreview(): Car {
            return Car().copy(
                name = "Land Rover",
                image = R.drawable.image_range_rover,
                price = "120k",
                rating = 5,
                consList = listOfString(),
                prosList = listOfString(),
            )
        }

        fun Companion.listOfCar(): List<Car> {
            return listOf(carPreview(), carPreview(), carPreview())
        }
    }
}
