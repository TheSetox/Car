package com.thesetox.car.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.thesetox.car.data.repository.CarRepository
import com.thesetox.car.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        val repository: CarRepository,
    ) : ViewModel() {
        private var _listOfCar: MutableState<List<Car>> = mutableStateOf(repository.listOfCar)

        val listOfCar: State<List<Car>> = _listOfCar

        val listOfMake = repository.listOfMake

        val listOfModel = repository.listOfModel

        fun onSelectedFilter(): (String, String) -> Unit =
            { make, model ->
                _listOfCar.value = repository.filterListOfCar(make, model)
            }
    }
