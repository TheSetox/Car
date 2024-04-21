package com.thesetox.car

import com.thesetox.car.data.source.impl.ImageDataSource
import org.junit.Assert
import org.junit.Test

class ImageDataSourceTest {
    @Test
    fun `when model is empty, return the image id of default launcher background`() {
        // Arrange
        val source = ImageDataSource()
        val model = ""

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.ic_launcher_background
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when model is not support, return the image id of default launcher background`() {
        // Arrange
        val source = ImageDataSource()
        val randomString = "not supported?"
        val model = randomString.random().toString()

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.ic_launcher_background
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when model is range rover, return the image id of range rover`() {
        // Arrange
        val source = ImageDataSource()
        val model = "Range Rover"

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.image_range_rover
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when model is roadster, return the image id of alpine roadster`() {
        // Arrange
        val source = ImageDataSource()
        val model = "Roadster"

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.image_alpine_roadster
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when model is 3300i, return the image id of bmw 3300i`() {
        // Arrange
        val source = ImageDataSource()
        val model = "3300i"

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.image_bmw_330i
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `when model is GLE coupe, return the image id of mercedes benz glc`() {
        // Arrange
        val source = ImageDataSource()
        val model = "GLE coupe"

        // Act
        val result = source.getImage(model)

        // Assert
        val expectedResult = R.drawable.image_mercedez_benz_glc
        Assert.assertEquals(expectedResult, result)
    }
}
