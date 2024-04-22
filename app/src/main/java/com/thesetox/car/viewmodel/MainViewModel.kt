package com.thesetox.car.viewmodel

import androidx.lifecycle.ViewModel
import com.thesetox.car.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        repository: CarRepository,
    ) : ViewModel() {
        val listOfCar = repository.listOfCar

        val listOfMake = repository.listOfMake

        val listOfModel = repository.listOfModel
    }
