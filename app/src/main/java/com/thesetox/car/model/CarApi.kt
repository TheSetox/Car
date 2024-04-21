package com.thesetox.car.model

import kotlinx.serialization.Serializable

@Serializable
data class CarApi(
    val customerPrice: Int,
    val make: String,
    val model: String,
    val rating: Int,
)
