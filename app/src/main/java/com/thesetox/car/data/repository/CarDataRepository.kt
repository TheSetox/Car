package com.thesetox.car.data.repository

import com.thesetox.car.data.database.CarEntity
import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.data.source.LocalSource
import com.thesetox.car.model.Car
import com.thesetox.car.model.CarApi
import javax.inject.Inject

class CarDataRepository
    @Inject
    constructor(
        private val imageSource: ImageSource,
        private val carSource: CarSource,
        private val localSource: LocalSource,
    ) : CarRepository {
        private suspend fun getListOfCar(): List<CarEntity> {
            return localSource.listOfCar().ifEmpty {
                val currentListOfCar = carSource.listOfCar.map { it.mapToCarEntity() }
                localSource.insertListOfCar(currentListOfCar)
                currentListOfCar
            }
        }

        override suspend fun listOfCar(): List<Car> = getListOfCar().map { it.mapToCar() }

        override suspend fun listOfMake(): List<String> = getListOfCar().map { it.make }

        override suspend fun listOfModel(): List<String> = getListOfCar().map { it.model }

        override suspend fun filterListOfCar(
            make: String,
            model: String,
        ): List<Car> {
            val filterList = getListOfCar().filter { it.make == make || it.model == model }
            // If empty, return default list. If not return the filtered list.
            return if (filterList.isEmpty()) listOfCar() else filterList.map { it.mapToCar() }
        }

        private fun CarApi.mapToCarEntity() =
            CarEntity(
                customerPrice = customerPrice,
                make = make,
                model = model,
                rating = rating,
                consList = consList,
                prosList = prosList,
            )

        private fun CarEntity.mapToCar() =
            Car(
                name = getName(this),
                image = imageSource.getImage(model),
                rating = minOf(rating, 5),
                price = getPrice(customerPrice),
                consList = consList.filter { cons -> cons.isNotEmpty() },
                prosList = prosList.filter { pros -> pros.isNotEmpty() },
            )

        private fun getName(car: CarEntity): String {
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
