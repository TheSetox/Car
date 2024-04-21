package com.thesetox.car.data.source.impl

import com.thesetox.car.R
import com.thesetox.car.data.source.ImageSource
import javax.inject.Inject

class ImageDataSource
    @Inject
    constructor() : ImageSource {
        private val mapOfImage =
            mapOf(
                "Range Rover" to R.drawable.image_range_rover,
                "Roadster" to R.drawable.image_alpine_roadster,
                "3300i" to R.drawable.image_bmw_330i,
                "GLE coupe" to R.drawable.image_mercedez_benz_glc,
            )

        override fun getImage(model: String): Int {
            val image = mapOfImage[model]
            return image ?: R.drawable.ic_launcher_background
        }
    }
