package com.thesetox.car

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.thesetox.car.data.source.impl.CarDataSource
import com.thesetox.car.model.CarApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarDataSourceTest {
    private var result = emptyList<CarApi>()

    @Before
    fun setup() {
        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val source = CarDataSource(context)

        // Act
        result = source.listOfCar
    }

    @Test
    fun listOfCarShouldNotBeEmpty() {
        // Assert
        Assert.assertTrue(result.isNotEmpty())
    }

    @Test
    fun listOfCarSizeIsFour() {
        // Assert
        val expectedResult = 4
        Assert.assertEquals(expectedResult, result.size)
    }

    /**
     * Need to check the models because it is being used as an id in the ImageDataSource.
     */
    @Test
    fun listOfCarHasNeededModel() {
        // Arrange
        val listOfModel = listOf("Range Rover", "Roadster", "3300i", "GLE coupe")
        val isModelIncluded =
            listOfModel.all { neededModel ->
                result.any { carApi -> carApi.model == neededModel }
            }

        // Assert
        Assert.assertTrue(isModelIncluded)
    }

    /**
     * Need to test if the specific `make` is included because it is
     * being used in the CarDataRepository.
     */
    @Test
    fun listOfCarHasNeededMake() {
        // Arrange
        val listOfMake = listOf("Land Rover", "Mercedes Benz")
        val isModelIncluded =
            listOfMake.all { neededMake ->
                result.any { carApi -> carApi.make == neededMake }
            }

        // Assert
        Assert.assertTrue(isModelIncluded)
    }
}
