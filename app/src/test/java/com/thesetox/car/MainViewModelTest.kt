package com.thesetox.car

import com.thesetox.car.data.repository.CarRepository
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.listOfCar
import com.thesetox.car.viewmodel.MainViewModel
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainViewModelTest {
    @Test
    fun `when listOfCar is called, confirm if listOfCar in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfCar } doReturn Car.listOfCar()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfCar

        // Assert
        verify(repository).listOfCar
    }

    @Test
    fun `when listOfCar is called, confirm if empty listOfCar in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfCar } doReturn emptyList()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfCar

        // Assert
        verify(repository).listOfCar
    }
}
