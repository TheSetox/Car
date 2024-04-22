package com.thesetox.car.repository

// TODO Update unit tests
class ListOfCarTest
// {
//    @Test
//    fun `when listOfCar is called, it should trigger listOfCar of carSource`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        repository.listOfCar
//
//        // Assert
//        verify(carSource).listOfCar
//    }
//
//    @Test
//    fun `when listOfCar is called, it should trigger listOfCar of carSource even if it is empty`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn emptyList()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        repository.listOfCar
//
//        // Assert
//        verify(carSource).listOfCar
//    }
//
//    @Test
//    fun `when the maker is Land Rover, it should return the model when getting the name`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "Range Rover" // Model
//        Assert.assertTrue(result.any { it.name == expectedResult })
//    }
//
//    @Test
//    fun `when the maker is Mercedes Benz, it should return the maker when getting the name`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "Mercedes Benz" // Maker
//        Assert.assertTrue(result.any { it.name == expectedResult })
//    }
//
//    @Test
//    fun `when the maker is not in the constraints, it should return both make and model`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "Alpine Roadster" // Combination of make and model
//        Assert.assertTrue(result.any { it.name == expectedResult })
//    }
//
//    @Test
//    fun `when listOfCar is called, imageSource should be triggered to get the image id`() {
//        // Arrange
//        val model = "Range Rover"
//        val imageSource =
//            mock<ImageSource> {
//                on { getImage(model) } doReturn 0
//            }
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        repository.listOfCar
//
//        // Assert
//        verify(imageSource).getImage(model)
//    }
//
//    @Test
//    fun `when listOfCar is called and it is empty, imageSource should not be triggered`() {
//        // Arrange
//        val model = ""
//        val imageSource =
//            mock<ImageSource> {
//                on { getImage(model) } doReturn 0
//            }
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn emptyList()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        repository.listOfCar
//
//        // Assert
//        verify(imageSource, never()).getImage(model)
//    }
//
//    @Test
//    fun `when listOfCar is called, it should not return a rating more than 5`() {
//        // Arrange
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn CarApi.listOfCarApi()
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.none { it.rating > 5 })
//    }
//
//    @Test
//    fun `when rating is more than 5, it should return 5`() {
//        // Arrange
//        val rating = Random.nextInt(5, 10)
//        val carApi =
//            CarApi(
//                customerPrice = 220_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = rating,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.rating == 5 })
//    }
//
//    @Test
//    fun `when rating is less than 5, it should return the correct rating`() {
//        // Arrange
//        val rating = Random.nextInt(1, 4)
//        val carApi =
//            CarApi(
//                customerPrice = 220_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = rating,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.rating == rating })
//    }
//
//    @Test
//    fun `when price is below 1_000, it should return the current price without modification`() {
//        // Arrange
//        val price = Random.nextInt(1, 999)
//        val carApi =
//            CarApi(
//                customerPrice = price,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.price == price.toString() })
//    }
//
//    @Test
//    fun `when price is below 1_200, it should return 1_2k`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 1_200,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "1.2k"
//        Assert.assertTrue(result.any { it.price == expectedResult })
//    }
//
//    @Test
//    fun `when price is below 9_999, it should return 9_9k`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 9_999,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "9.9k"
//        Assert.assertTrue(result.any { it.price == expectedResult })
//    }
//
//    @Test
//    fun `when price is above 10_000, it should return the value divide by 1_000`() {
//        // Arrange
//        val price = Random.nextInt(10_000, 999_999)
//        val carApi =
//            CarApi(
//                customerPrice = price,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        val expectedResult = "${price / 1000}k"
//        Assert.assertTrue(result.any { it.price == expectedResult })
//    }
//
//    @Test
//    fun `when listOfCar is called, it should return the cons even if it is empty`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.consList.isEmpty() })
//    }
//
//    @Test
//    fun `when listOfCar is called, it should return the cons`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = listOf("Something"),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.consList.isNotEmpty() })
//    }
//
//    @Test
//    fun `when listOfCar is called and only one cons is not empty, the cons list size is one`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = listOf("", "Not Empty", ""),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.consList.size == 1 })
//    }
//
//    @Test
//    fun `when listOfCar is called and two cons is not empty, the cons list size is two`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = listOf("Not Empty", "", "Not Empty"),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.consList.size == 2 })
//    }
//
//    @Test
//    fun `when listOfCar is called, it should return the pros even if it is empty`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = emptyList(),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.prosList.isEmpty() })
//    }
//
//    @Test
//    fun `when listOfCar is called, it should return the pros`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = listOf("Something"),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.prosList.isNotEmpty() })
//    }
//
//    @Test
//    fun `when listOfCar is called and only one pros is not empty, the pros list size is one`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = listOf("", "Not Empty", ""),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.prosList.size == 1 })
//    }
//
//    @Test
//    fun `when listOfCar is called and two pros is not empty, the pros list size is two`() {
//        // Arrange
//        val carApi =
//            CarApi(
//                customerPrice = 10_000,
//                make = "Alpine",
//                model = "Roadster",
//                rating = 5,
//                consList = emptyList(),
//                prosList = listOf("Not Empty", "", "Not Empty"),
//            )
//        val imageSource = mock<ImageSource>()
//        val carSource =
//            mock<CarSource> {
//                on { listOfCar } doReturn listOf(carApi)
//            }
//        val repository = CarDataRepository(imageSource, carSource)
//
//        // Act
//        val result = repository.listOfCar
//
//        // Assert
//        Assert.assertTrue(result.any { it.prosList.size == 2 })
//    }
// }
