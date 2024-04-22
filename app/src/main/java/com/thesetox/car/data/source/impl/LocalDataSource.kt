package com.thesetox.car.data.source.impl

import com.thesetox.car.data.database.AppDatabase
import com.thesetox.car.data.database.CarEntity
import com.thesetox.car.data.source.LocalSource
import javax.inject.Inject

class LocalDataSource
    @Inject
    constructor(appDatabase: AppDatabase) : LocalSource {
        private val carDao = appDatabase.carDao()

        override suspend fun insertListOfCar(list: List<CarEntity>) {
            carDao.insertCars(list)
        }

        override suspend fun listOfCar(): List<CarEntity> {
            return carDao.getListOfCar()
        }
    }
