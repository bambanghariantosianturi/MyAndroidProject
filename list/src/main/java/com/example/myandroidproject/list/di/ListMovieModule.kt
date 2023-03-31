package com.example.myandroidproject.list.di

import androidx.lifecycle.ViewModelProvider
import com.example.myandroidproject.core.ui.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ListMovieModule {

    @Binds
    @ViewModelScoped
    abstract fun bindListViewModel(factory: MainViewModelFactory): ViewModelProvider.Factory
}