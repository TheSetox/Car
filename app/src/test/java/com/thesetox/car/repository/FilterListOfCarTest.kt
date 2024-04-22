package com.thesetox.car.repository

import com.thesetox.car.data.repository.CarDataRepository
import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.model.CarApi
import com.thesetox.car.model.CarApi.Companion.listOfCarApi
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FilterListOfCarTest {
    @Test
    fun `when filter matches the data, confirm if listOfCar of carSource is called`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        repository.filterListOfCar("Land Rover", "Range Rover")

        // Assert
        verify(carSource).listOfCar
    }

    @Test
    fun `when filter matches one data, confirm if the size of filterListOfCar is one`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.filterListOfCar("Land Rover", "Range Rover").size

        // Assert
        val expectedResult = 1
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when filter matches two data, confirm if the size of filterListOfCar is one`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.filterListOfCar("Land Rover", "Roadster").size

        // Assert
        val expectedResult = 2
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when filter does not match, confirm if we return the unfiltered list using the size`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.filterListOfCar("", "").size

        // Assert
        val expectedResult = carSource.listOfCar.size
        Assert.assertEquals(expectedResult, result)
    }
}
