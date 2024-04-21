package com.thesetox.car.model

import com.thesetox.car.R

data class Car(
    val name: String = "",
    val image: Int = 0,
    val rating: Int = 0,
    val price: String = "0",
) {
    companion object {
        fun Companion.carPreview(): Car {
            return Car().copy(
                name = "Land Rover",
                image = R.drawable.image_range_rover,
                price = "120k",
                rating = 5,
            )
        }

        fun Companion.listOfCar(): List<Car> {
            return listOf(carPreview(), carPreview(), carPreview())
        }
    }
}
