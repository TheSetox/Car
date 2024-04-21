package com.thesetox.car.data.source

interface ImageSource {
    fun getImage(model: String): Int
}
