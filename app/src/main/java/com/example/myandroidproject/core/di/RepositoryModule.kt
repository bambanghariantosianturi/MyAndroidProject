package com.example.myandroidproject.core.di

import com.example.myandroidproject.core.data.DataRepository
import com.example.myandroidproject.core.data.source.local.LocalDataSource
import com.example.myandroidproject.core.data.source.remote.RemoteDataSource
import com.example.myandroidproject.core.domain.repository.IDataRepository
import com.example.myandroidproject.core.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module(includes = [NetworkModule::class, DatabaseModule::class])
//abstract class RepositoryModule {
//
//    @Binds
//    abstract fun provideRepository(dataRepository: DataRepository): IDataRepository
//}

//@Module(includes = [NetworkModule::class, DatabaseModule::class])
//class RepositoryModule {
//
//    @Singleton
//    @Provides
//    fun provideRepository(remote: RemoteDataSource, local: LocalDataSource, executors: AppExecutors): IDataRepository = DataRepository(remote, local, executors)
//}

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(dataRepository: DataRepository): IDataRepository
}

