package com.example.myandroidproject.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myandroidproject.core.data.source.local.LocalDataSource
import com.example.myandroidproject.core.data.source.remote.RemoteDataSource
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.response.DataResponse
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.repository.IDataRepository
import com.example.myandroidproject.core.utils.AppExecutors
import com.example.myandroidproject.core.utils.DataMapper
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IDataRepository {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ) : DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getAllData(): kotlinx.coroutines.flow.Flow<Resource<List<Data>>> {
        return object : NetworkBoundResource<List<Data>, List<DataResponse>>() {
            override fun loadFromDB(): kotlinx.coroutines.flow.Flow<List<Data>> {
//                return Transformations.map(localDataSource.getAllData()) {
//                    DataMapper.mapEntitiesToDomain(it)
//                }
                return localDataSource.getAllData().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Data>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): kotlinx.coroutines.flow.Flow<ApiResponse<List<DataResponse>>> {
                return remoteDataSource.getDataUser()
            }

            override suspend fun saveCallResult(data: List<DataResponse>) {
               val list = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertData(list)
            }
        }.asFlow()
    }

    override fun getFavoriteData(): kotlinx.coroutines.flow.Flow<List<Data>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteData(data: Data, state: Boolean) {
        TODO("Not yet implemented")
    }
}