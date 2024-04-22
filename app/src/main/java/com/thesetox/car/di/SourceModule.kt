package com.thesetox.car.di

import com.thesetox.car.data.source.CarSource
import com.thesetox.car.data.source.ImageSource
import com.thesetox.car.data.source.LocalSource
import com.thesetox.car.data.source.impl.CarDataSource
import com.thesetox.car.data.source.impl.ImageDataSource
import com.thesetox.car.data.source.impl.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourceModule {
    @Binds
    abstract fun bindCarSource(source: CarDataSource): CarSource

    @Binds
    abstract fun bindImageSource(source: ImageDataSource): ImageSource

    @Binds
    abstract fun bindLocalSource(source: LocalDataSource): LocalSource
}
