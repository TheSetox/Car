package com.thesetox.car.data.source

import com.thesetox.car.model.CarApi

interface CarSource {
    val listOfCar: List<CarApi>
}
