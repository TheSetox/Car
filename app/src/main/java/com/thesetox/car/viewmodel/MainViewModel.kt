package com.thesetox.car.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesetox.car.data.repository.CarRepository
import com.thesetox.car.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val repository: CarRepository,
    ) : ViewModel() {
        private val _listOfCarState = MutableStateFlow<List<Car>>(listOf())
        val listOfCarState: StateFlow<List<Car>> get() = _listOfCarState

        private var _listOfMakeState: MutableState<List<String>> = mutableStateOf(listOf())
        val listOfMakeState: State<List<String>> = _listOfMakeState

        private var _listOfModelState: MutableState<List<String>> = mutableStateOf(listOf())
        val listOfModelState: State<List<String>> = _listOfModelState

        fun onAction(action: Action) {
            viewModelScope.launch {
                when (action) {
                    Action.LoadCarList -> getListOfCar()
                    Action.LoadMakeList -> getListOfMake()
                    Action.LoadModelList -> getListOfModel()
                    is Action.FilterList -> onSelectedFilter(action.make, action.model)
                }
            }
        }

        private suspend fun onSelectedFilter(
            make: String,
            model: String,
        ) {
            _listOfCarState.value = repository.filterListOfCar(make, model)
        }

        private suspend fun getListOfCar() {
            _listOfCarState.value = repository.listOfCar()
        }

        private suspend fun getListOfMake() {
            _listOfMakeState.value = repository.listOfMake()
        }

        private suspend fun getListOfModel() {
            _listOfModelState.value = repository.listOfModel()
        }
    }

sealed interface Action {
    data object LoadCarList : Action

    data object LoadMakeList : Action

    data object LoadModelList : Action

    data class FilterList(val make: String, val model: String) : Action
}
