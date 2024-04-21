package com.thesetox.car.data.repository

import com.thesetox.car.model.Car

interface CarRepository {
    val listOfCar: List<Car>
}
