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

class ListOfModelTest {
    @Test
    fun `when listOfModel is called, confirm if listOfCar of carSource is called`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        repository.listOfModel

        // Assert
        verify(carSource).listOfCar
    }

    @Test
    fun `when listOfModel is called, the size is the same when fetching listOfCar in carSource`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn CarApi.listOfCarApi()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.listOfModel.size

        // Assert
        val expectedResult = carSource.listOfCar.size
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when listOfModel is called and it is empty, it should return an empty result`() {
        // Arrange
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn emptyList()
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.listOfModel

        // Assert
        val expectedResult = emptyList<String>()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when listOfModel is called, it should get the expected result`() {
        // Arrange
        val list = CarApi.listOfCarApi()
        val imageSource = mock<ImageSource>()
        val carSource =
            mock<CarSource> {
                on { listOfCar } doReturn list
            }
        val repository = CarDataRepository(imageSource, carSource)

        // Act
        val result = repository.listOfModel

        // Assert
        val expectedResult = list.map { it.model }
        Assert.assertEquals(expectedResult, result)
    }
}
