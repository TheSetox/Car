package com.thesetox.car

import com.thesetox.car.data.repository.CarRepository
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.listOfCar
import com.thesetox.car.model.Car.Companion.listOfString
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

    @Test
    fun `when listOfMake is called, confirm if listOfMake in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfMake } doReturn Car.listOfString()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfMake

        // Assert
        verify(repository).listOfMake
    }

    @Test
    fun `when listOfMake is called, confirm if empty listOfMake in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfMake } doReturn emptyList()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfMake

        // Assert
        verify(repository).listOfMake
    }

    @Test
    fun `when listOfModel is called, confirm if listOfModel in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfModel } doReturn Car.listOfString()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfModel

        // Assert
        verify(repository).listOfModel
    }

    @Test
    fun `when listOfModel is called, confirm if empty listOfModel in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfModel } doReturn emptyList()
            }
        val viewModel = MainViewModel(repository)

        // Act
        viewModel.listOfModel

        // Assert
        verify(repository).listOfModel
    }

    @Test
    fun `when filter is needed, confirm if filterListOfCar in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { filterListOfCar("", "") } doReturn Car.listOfCar()
            }
        val viewModel = MainViewModel(repository)

        // Act
        val applyFilter = viewModel.onSelectedFilter()
        applyFilter("", "")

        // Assert
        verify(repository).filterListOfCar("", "")
    }

    @Test
    fun `when filter is needed, confirm if empty filterListOfCar in repository is called`() {
        // Arrange
        val repository =
            mock<CarRepository> {
                on { listOfCar } doReturn Car.listOfCar()
                on { filterListOfCar("", "") } doReturn emptyList()
            }
        val viewModel = MainViewModel(repository)

        // Act
        val applyFilter = viewModel.onSelectedFilter()
        applyFilter("", "")

        // Assert
        verify(repository).listOfCar
    }
}
