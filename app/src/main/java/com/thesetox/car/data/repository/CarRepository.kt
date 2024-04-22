package com.thesetox.car.data.repository

import com.thesetox.car.model.Car

interface CarRepository {
    val listOfCar: List<Car>

    val listOfMake: List<String>

    val listOfModel: List<String>

    fun filterListOfCar(
        make: String,
        model: String,
    ): List<Car>
}
