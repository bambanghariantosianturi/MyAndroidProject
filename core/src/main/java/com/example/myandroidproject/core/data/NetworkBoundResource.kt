package com.example.myandroidproject.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.utils.AppExecutors

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }

        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.Loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    mExecutors.diskIO().execute {
                        saveCallResult(response.data)
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) {
                                result.value = Resource.Success(it)
                            }
                        }
                    }
                is ApiResponse.Empty -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) {
                        result.value = Resource.Success(it)
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = Resource.Error(response.errorMessage, it)
                    }
                }
            }

        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}