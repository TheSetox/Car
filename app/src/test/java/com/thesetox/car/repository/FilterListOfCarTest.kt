package com.thesetox.car.repository

import com.thesetox.car.data.database.CarEntity
import com.thesetox.car.data.database.CarEntity.Companion.listOfCarEntity
import com.thesetox.car.data.repository.CarDataRepository
import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.data.source.LocalSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FilterListOfCarTest {
    @Test
    fun `when filter matches the data, confirm if listOfCar of localSource is called`() =
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.filterListOfCar("Land Rover", "Range Rover")

            // Assert
            verify(localSource).listOfCar()
        }

    @Test
    fun `when filter matches one data, confirm if the size of filterListOfCar is one`() =
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.filterListOfCar("Land Rover", "Range Rover").size

            // Assert
            val expectedResult = 1
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when filter matches two data, confirm if the size of filterListOfCar is one`() =
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.filterListOfCar("Land Rover", "Roadster").size

            // Assert
            val expectedResult = 2
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when filter does not match, confirm if we return the unfiltered list using the size`() =
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.filterListOfCar("", "").size

            // Assert
            val expectedResult = localSource.listOfCar().size
            Assert.assertEquals(expectedResult, result)
        }
}
