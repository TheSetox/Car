package com.thesetox.car.data.repository

import com.thesetox.car.model.Car

interface CarRepository {
    suspend fun listOfCar(): List<Car>

    suspend fun listOfMake(): List<String>

    suspend fun listOfModel(): List<String>

    suspend fun filterListOfCar(
        make: String,
        model: String,
    ): List<Car>
}
