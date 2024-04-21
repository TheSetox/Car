package com.thesetox.car.data.source.impl

import android.content.Context
import android.util.Log
import com.thesetox.car.data.source.CarSource
import com.thesetox.car.model.CarApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CarDataSource
    @Inject
    constructor(
        @ApplicationContext context: Context,
    ) : CarSource {
        private val fileName = "car_list.json"
        private val emptyString = ""

        private val jsonString =
            try {
                context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            } catch (e: Exception) {
                Log.d("CarDataSource", ": ${e.message}")
                // return empty string
                emptyString
            }

        private val json = Json { ignoreUnknownKeys = true }

        override val listOfCar: List<CarApi> = json.decodeFromString(jsonString)
    }
