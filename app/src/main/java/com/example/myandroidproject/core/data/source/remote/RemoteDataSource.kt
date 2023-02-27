package com.example.myandroidproject.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.network.ApiService
import com.example.myandroidproject.core.data.source.remote.response.DataResponse
import com.example.myandroidproject.core.data.source.remote.response.UserResponse
import com.example.myandroidproject.core.utils.JsonHelper
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

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

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getDataUser(): LiveData<ApiResponse<List<DataResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<DataResponse>>>()

        val client = apiService.getDataUser()
        client.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                val dataResponse = response.body()?.items
                resultData.value = if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.e("remotedatasource", response.body().toString())
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("remotedatasource", t.message.toString())
            }
        })
        return  resultData
    }
}



