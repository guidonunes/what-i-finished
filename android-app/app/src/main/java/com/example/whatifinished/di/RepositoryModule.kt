package com.example.whatifinished.di

import com.example.whatifinished.repository.ActivityRepository
import com.example.whatifinished.repository.ActivityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindActivityRepository(
        activityRepositoryImpl: ActivityRepositoryImpl
    ): ActivityRepository
}
