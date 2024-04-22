package com.thesetox.car.data.repository

import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.model.Car
import com.thesetox.car.model.CarApi
import javax.inject.Inject

class CarDataRepository
    @Inject
    constructor(
        private val imageSource: ImageSource,
        carSource: CarSource,
    ) : CarRepository {
        private val _listOfCar = carSource.listOfCar

        override val listOfCar: List<Car> = _listOfCar.map { it.mapToCar() }

        override val listOfMake: List<String> = _listOfCar.map { it.make }

        override val listOfModel: List<String> = _listOfCar.map { it.model }

        override fun filterListOfCar(
            make: String,
            model: String,
        ): List<Car> {
            val filterList = _listOfCar.filter { it.make == make || it.model == model }
            // If empty, return default list. If not return the filtered list.
            return if (filterList.isEmpty()) listOfCar else filterList.map { it.mapToCar() }
        }

        private fun CarApi.mapToCar() =
            Car(
                name = getName(this),
                image = imageSource.getImage(model),
                rating = minOf(rating, 5),
                price = getPrice(customerPrice),
                consList = consList.filter { cons -> cons.isNotEmpty() },
                prosList = prosList.filter { pros -> pros.isNotEmpty() },
            )

        private fun getName(car: CarApi): String {
            return when (car.make) {
                "Land Rover" -> car.model // Return
                "Mercedes Benz" -> car.make
                else -> "${car.make} ${car.model}"
            }
        }

        private fun getPrice(price: Int): String {
            return when {
                // 999 below
                price < 1000 -> price.toString()
                // 1_000 to 9_999.
                price < 10000 -> "${price.divideBy1k()}.${price.getDecimalValue()}k"
                // 10_000 onwards.
                else -> "${price.divideBy1k()}k"
            }
        }

        private fun Int.divideBy1k() = this / 1000

        private fun Int.getDecimalValue() = this % 1000 / 100
    }
