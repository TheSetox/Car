package com.thesetox.car.data.source

import com.thesetox.car.data.database.CarEntity

interface LocalSource {
    suspend fun insertListOfCar(list: List<CarEntity>)

    suspend fun listOfCar(): List<CarEntity>
}
