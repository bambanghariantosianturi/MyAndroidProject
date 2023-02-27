package com.example.myandroidproject.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myandroidproject.presentation.MainViewModel
import com.example.myandroidproject.presentation.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//@Suppress("unused")
//@Module
//abstract class ViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
//
////    @Binds
////    @IntoMap
////    @ViewModelKey(FavoriteViewModel::class)
////    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
////
////    @Binds
////    @IntoMap
////    @ViewModelKey(DetailTourismViewModel::class)
////    abstract fun bindDetailTourismViewModel(viewModel: DetailTourismViewModel): ViewModel
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
//}