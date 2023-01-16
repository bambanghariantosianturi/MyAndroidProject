package com.example.myandroidproject.core.data.source.remote

import android.util.Log
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.network.ApiService
import com.example.myandroidproject.core.data.source.remote.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(helper: JsonHelper): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(helper)
//            }
//    }
//
//    fun getAllData(): LiveData<ApiResponse<List<DataResponse>>> {
//        val resultData = MutableLiveData<ApiResponse<List<DataResponse>>>()
//
//        //get data from local json
//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({
//            try {
//                val dataArray = jsonHelper.loadData()
//                if (dataArray.isNotEmpty()) {
//                    resultData.value = ApiResponse.Success(dataArray)
//                } else {
//                    resultData.value = ApiResponse.Empty
//                }
//            } catch (e: JSONException) {
//                resultData.value = ApiResponse.Error(e.toString())
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }, 2000)
//        return resultData
//    }
//}

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getDataUser(): Flow<ApiResponse<List<DataResponse>>> {
//        val resultData = MutableLiveData<ApiResponse<List<DataResponse>>>()

//        val client = apiService.getDataUser()
//        client.enqueue(object : Callback<UserResponse?> {
//            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
//                val dataResponse = response.body()?.items
//                resultData.value = if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
//                Log.e("remotedatasource", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
//                resultData.value = ApiResponse.Error(t.message.toString())
//                Log.e("remotedatasource", t.message.toString())
//            }
//        })
//        return  resultData

        return flow {
            try {
                val response = apiService.getDataUser()
                val dataArray = response.items
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}



