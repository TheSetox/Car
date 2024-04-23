package com.thesetox.car

import com.thesetox.car.data.repository.CarRepository
import com.thesetox.car.model.Car
import com.thesetox.car.model.Car.Companion.listOfCar
import com.thesetox.car.model.Car.Companion.listOfString
import com.thesetox.car.viewmodel.Action
import com.thesetox.car.viewmodel.MainViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when listOfCar is called, confirm if listOfCar in repository is called`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfCar() } doReturn Car.listOfCar()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadCarList)

            // Assert
            verify(repository).listOfCar()
        }

    @Test
    fun `when listOfCar is called, confirm if empty listOfCar in repository is called`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadCarList)

            // Assert
            verify(repository).listOfCar()
        }

    @Test
    fun `when listOfCar is called, confirm if listOfCarState can get the result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfCar() } doReturn Car.listOfCar()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadCarList)
            val result = viewModel.listOfCarState.value

            // Assert
            val expectedResult = Car.listOfCar()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when listOfCar is called, confirm if listOfCarState can get the empty result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadCarList)
            val result = viewModel.listOfCarState.value

            // Assert
            val expectedResult = emptyList<Car>()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when listOfMake is called, confirm if listOfMake in repository is called`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfMake() } doReturn Car.listOfString()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadMakeList)

            // Assert
            verify(repository).listOfMake()
        }

    @Test
    fun `when listOfMake is called, confirm if empty listOfMake in repository is called`() {
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfMake() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadMakeList)

            // Assert
            verify(repository).listOfMake()
        }
    }

    @Test
    fun `when listOfMake is called, confirm if listOfMakeState can get the result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfMake() } doReturn Car.listOfString()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadMakeList)
            val result = viewModel.listOfMakeState.value

            // Assert
            val expectedResult = Car.listOfString()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when listOfMake is called, confirm if listOfMakeState can get the empty result`() {
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfMake() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadMakeList)
            val result = viewModel.listOfMakeState.value

            // Assert
            val expectedResult = emptyList<String>()
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `when listOfModel is called, confirm if listOfModel in repository is called`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfModel() } doReturn Car.listOfString()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadModelList)

            // Assert
            verify(repository).listOfModel()
        }

    @Test
    fun `when listOfModel is called, confirm if empty listOfModel in repository is called`() {
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfModel() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadModelList)

            // Assert
            verify(repository).listOfModel()
        }
    }

    @Test
    fun `when listOfModel is called, confirm if listOfModelState can get the result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfModel() } doReturn Car.listOfString()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadModelList)
            val result = viewModel.listOfModelState.value

            // Assert
            val expectedResult = Car.listOfString()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when listOfModel is called, confirm if listOfMakeState can get the empty result`() {
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { listOfModel() } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.LoadModelList)
            val result = viewModel.listOfModelState.value

            // Assert
            val expectedResult = emptyList<String>()
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `when filter is needed, confirm if filterListOfCar in repository is called`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { filterListOfCar("", "") } doReturn Car.listOfCar()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.FilterList("", ""))

            // Assert
            verify(repository).filterListOfCar("", "")
        }

    @Test
    fun `when filter is needed, confirm if empty listOfMakeState in repository is called`() {
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { filterListOfCar("", "") } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.FilterList("", ""))

            // Assert
            verify(repository).filterListOfCar("", "")
        }
    }

    @Test
    fun `when filter is needed, confirm if listOfCarState can get the empty result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { filterListOfCar("", "") } doReturn emptyList()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.FilterList("", ""))
            val result = viewModel.listOfCarState.value

            // Assert
            val expectedResult = emptyList<Car>()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when filter is needed, confirm if listOfCarState can get the result`() =
        runTest {
            // Arrange
            val repository =
                mock<CarRepository> {
                    onBlocking { filterListOfCar("", "") } doReturn Car.listOfCar()
                }
            val viewModel = MainViewModel(repository)

            // Act
            viewModel.onAction(Action.FilterList("", ""))
            val result = viewModel.listOfCarState.value

            // Assert
            val expectedResult = Car.listOfCar()
            Assert.assertEquals(expectedResult, result)
        }
}
