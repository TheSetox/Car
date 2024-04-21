package com.thesetox.car.data.repository

import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.model.Car
import com.thesetox.car.model.CarApi
import javax.inject.Inject

class CarDataRepository
    @Inject
    constructor(
        imageSource: ImageSource,
        carSource: CarSource,
    ) : CarRepository {
        override val listOfCar: List<Car> =
            carSource.listOfCar.map {
                Car(
                    name = getName(it),
                    image = imageSource.getImage(it.model),
                    rating = minOf(it.rating, 5),
                    price = getPrice(it.customerPrice),
                    consList = it.consList.filter { cons -> cons.isNotEmpty() },
                    prosList = it.prosList.filter { pros -> pros.isNotEmpty() },
                )
            }

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
