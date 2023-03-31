package com.example.myandroidproject.core.di

import android.content.Context
import com.example.myandroidproject.core.domain.repository.IDataRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//@Singleton
//@Component(modules = [RepositoryModule::class])
//interface CoreComponent {
//
//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): CoreComponent
//    }
//
//    fun provideRepository(): IDataRepository
//}