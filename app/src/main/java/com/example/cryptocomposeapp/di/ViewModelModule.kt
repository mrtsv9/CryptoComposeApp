package com.example.cryptocomposeapp.di

import com.example.cryptocomposeapp.ui.main_screen.MainRepository
import com.example.cryptocomposeapp.ui.main_screen.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMainViewModel(repository: MainRepository): MainViewModel = MainViewModel(repository)

}