package com.thesetox.car.repository

import com.thesetox.car.data.database.CarEntity
import com.thesetox.car.data.database.CarEntity.Companion.listOfCarEntity
import com.thesetox.car.data.repository.CarDataRepository
import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.data.source.LocalSource
import com.thesetox.car.model.CarApi
import com.thesetox.car.model.CarApi.Companion.listOfCarApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import kotlin.random.Random

class ListOfCarTest {
    @Test
    fun `when listOfCar is called, it should trigger listOfCar of localSource`() =
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
            repository.listOfCar()

            // Assert
            verify(localSource).listOfCar()
        }

    @Test
    fun `when listOfCar is called, it should trigger empty listOfCar of localSource`() {
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.listOfCar()

            // Assert
            verify(localSource).listOfCar()
        }
    }

    @Test
    fun `when local data is empty, confirm if we can call the function in carSource`() {
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource =
                mock<CarSource> {
                    on { listOfCar } doReturn CarApi.listOfCarApi()
                }
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.listOfCar()

            // Assert
            verify(carSource).listOfCar
        }
    }

    @Test
    fun `when local data is empty, it should return the same size of data in the carSource`() {
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource =
                mock<CarSource> {
                    on { listOfCar } doReturn CarApi.listOfCarApi()
                }
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar().size

            // Assert
            val expectedResult = CarApi.listOfCarApi().size
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `when local data is empty, confirm if saving the carSource to localSource is triggered`() {
        runTest {
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource =
                mock<CarSource> {
                    on { listOfCar } doReturn CarApi.listOfCarApi()
                }
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.listOfCar()

            // Assert
            verify(localSource).insertListOfCar(CarEntity.listOfCarEntity())
        }
    }

    @Test
    fun `when local data is empty, it should return the data in the carSource even if empty`() {
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
            val result = repository.listOfCar()

            // Assert
            val expectedResult = emptyList<CarEntity>()
            Assert.assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `when the maker is Land Rover, it should return the model when getting the name`() {
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
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "Range Rover" // Model
            Assert.assertTrue(result.any { it.name == expectedResult })
        }
    }

    @Test
    fun `when the maker is Mercedes Benz, it should return the maker when getting the name`() {
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
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "Mercedes Benz" // Maker
            Assert.assertTrue(result.any { it.name == expectedResult })
        }
    }

    @Test
    fun `when the maker is not in the constraints, it should return both make and model`() {
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
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "Alpine Roadster" // Combination of make and model
            Assert.assertTrue(result.any { it.name == expectedResult })
        }
    }

    @Test
    fun `when listOfCar is called, imageSource should be triggered to get the image id`() {
        runTest {
            // Arrange
            val model = "Range Rover"
            val carSource = mock<CarSource>()
            val imageSource =
                mock<ImageSource> {
                    on { getImage(model) } doReturn 0
                }
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.listOfCar()

            // Assert
            verify(imageSource).getImage(model)
        }
    }

    @Test
    fun `when listOfCar is called and it is empty, imageSource should not be triggered`() {
        runTest {
            // Arrange
            val model = ""
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn emptyList()
                }
            val imageSource =
                mock<ImageSource> {
                    on { getImage(model) } doReturn 0
                }
            val carSource =
                mock<CarSource> {
                    on { listOfCar } doReturn emptyList()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            repository.listOfCar()

            // Assert
            verify(imageSource, never()).getImage(model)
        }
    }

    @Test
    fun `when listOfCar is called, it should not return a rating more than 5`() =
        runTest {
            // Arrange
            // Arrange
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn CarEntity.listOfCarEntity()
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.none { it.rating > 5 })
        }

    @Test
    fun `when rating is more than 5, it should return 5`() =
        runTest {
            // Arrange
            val rating = Random.nextInt(5, 10)
            val carEntity =
                CarEntity(
                    customerPrice = 220_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = rating,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.rating == 5 })
        }

    @Test
    fun `when rating is less than 5, it should return the correct rating`() =
        runTest {
            // Arrange
            val rating = Random.nextInt(1, 4)
            val carEntity =
                CarEntity(
                    customerPrice = 220_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = rating,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.rating == rating })
        }

    @Test
    fun `when price is below 1_000, it should return the current price without modification`() {
        runTest {
            // Arrange
            val price = Random.nextInt(1, 999)
            val carEntity =
                CarEntity(
                    customerPrice = price,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.price == price.toString() })
        }
    }

    @Test
    fun `when price is below 1_200, it should return 1_2k`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 1_200,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "1.2k"
            Assert.assertTrue(result.any { it.price == expectedResult })
        }

    @Test
    fun `when price is below 9_999, it should return 9_9k`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 9_999,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "9.9k"
            Assert.assertTrue(result.any { it.price == expectedResult })
        }

    @Test
    fun `when price is above 10_000, it should return the value divide by 1_000`() =
        runTest {
            // Arrange
            val price = Random.nextInt(10_000, 999_999)
            val carEntity =
                CarEntity(
                    customerPrice = price,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            val expectedResult = "${price / 1000}k"
            Assert.assertTrue(result.any { it.price == expectedResult })
        }

    @Test
    fun `when listOfCar is called, it should return the cons even if it is empty`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.consList.isEmpty() })
        }

    @Test
    fun `when listOfCar is called, it should return the cons`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = listOf("Something"),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.consList.isNotEmpty() })
        }

    @Test
    fun `when listOfCar is called and only one cons is not empty, the cons list size is one`() {
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = listOf("", "Not Empty", ""),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.consList.size == 1 })
        }
    }

    @Test
    fun `when listOfCar is called and two cons is not empty, the cons list size is two`() {
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = listOf("Not Empty", "", "Not Empty"),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.consList.size == 2 })
        }
    }

    @Test
    fun `when listOfCar is called, it should return the pros even if it is empty`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = emptyList(),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.prosList.isEmpty() })
        }

    @Test
    fun `when listOfCar is called, it should return the pros`() =
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = listOf("Something"),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.prosList.isNotEmpty() })
        }

    @Test
    fun `when listOfCar is called and only one pros is not empty, the pros list size is one`() {
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = listOf("", "Not Empty", ""),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.prosList.size == 1 })
        }
    }

    @Test
    fun `when listOfCar is called and two pros is not empty, the pros list size is two`() {
        runTest {
            // Arrange
            val carEntity =
                CarEntity(
                    customerPrice = 10_000,
                    make = "Alpine",
                    model = "Roadster",
                    rating = 5,
                    consList = emptyList(),
                    prosList = listOf("Not Empty", "", "Not Empty"),
                )
            val imageSource = mock<ImageSource>()
            val carSource = mock<CarSource>()
            val localSource =
                mock<LocalSource> {
                    onBlocking { listOfCar() } doReturn listOf(carEntity)
                }
            val repository = CarDataRepository(imageSource, carSource, localSource)

            // Act
            val result = repository.listOfCar()

            // Assert
            Assert.assertTrue(result.any { it.prosList.size == 2 })
        }
    }
}
