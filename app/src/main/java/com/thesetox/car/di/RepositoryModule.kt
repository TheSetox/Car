package com.thesetox.car.di

import com.thesetox.car.data.repository.CarDataRepository
import com.thesetox.car.data.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCarRepository(repository: CarDataRepository): CarRepository
}
