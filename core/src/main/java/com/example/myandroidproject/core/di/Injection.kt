package com.example.myandroidproject.core.di

import android.content.Context
import com.example.myandroidproject.core.data.DataRepository
import com.example.myandroidproject.core.data.source.local.LocalDataSource
import com.example.myandroidproject.core.data.source.local.room.UserDatabase
import com.example.myandroidproject.core.data.source.remote.RemoteDataSource
//import com.example.myandroidproject.core.data.source.remote.network.ApiConfig
import com.example.myandroidproject.core.domain.repository.IDataRepository
import com.example.myandroidproject.core.domain.usecase.DataInteractor
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import com.example.myandroidproject.core.utils.AppExecutors
//import com.example.myandroidproject.core.utils.JsonHelper

//object Injection {
//    private fun provideRepository(context: Context): IDataRepository {
//        val database = UserDatabase.getInstance(context)
//
////        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.userDao())
//        val appExecutors = AppExecutors()
//
//        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
//    }
//
//    fun provideDataUseCase(context: Context): DataUseCase {
//        val repository = provideRepository(context)
//        return DataInteractor(repository)
//    }
//}