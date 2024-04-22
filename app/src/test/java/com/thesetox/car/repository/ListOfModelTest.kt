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

class ListOfModelTest {
    @Test
    fun `when listOfModel is called, confirm if listOfCar of localSource is called`() =
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
            repository.listOfModel()

            // Assert
            verify(localSource).listOfCar()
        }

    @Test
    fun `when listOfModel is called, the size is the same when fetching listOfCar in carSource`() {
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
            val result = repository.listOfModel().size

            // Assert
            val expectedResult = localSource.listOfCar().size
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `when listOfModel is called and it is empty, it should return an empty result`() =
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource =
                mock<CarSource> {
                    on { listOfCar } doReturn emptyList()
                }
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfModel()

            // Assert
            val expectedResult = emptyList<String>()
            Assert.assertEquals(expectedResult, result)
        }

    @Test
    fun `when listOfModel is called, it should get the expected result`() =
        runTest {
            // Arrange
            val list = CarEntity.listOfCarEntity()
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfModel()

            // Assert
            val expectedResult = list.map { it.model }
            Assert.assertEquals(expectedResult, result)
        }
}
