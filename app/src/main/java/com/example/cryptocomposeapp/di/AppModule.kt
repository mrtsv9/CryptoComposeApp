package com.example.cryptocomposeapp.di

import com.example.cryptocomposeapp.domain.service.ApiService
import com.example.cryptocomposeapp.presentation.details_screen.DetailsRepository
import com.example.cryptocomposeapp.presentation.main_screen.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainRepository = MainRepository(apiService)

    @Provides
    @Singleton
    fun provideDetailsRepository(apiService: ApiService): DetailsRepository =
        DetailsRepository(apiService)

}