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
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    //get data with retrofit
//    fun getDataUser(): LiveData<ApiResponse<List<DataResponse>>> {
//        val resultData = MutableLiveData<ApiResponse<List<DataResponse>>>()
//
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
//    }

    //get data with rx
    fun getDataUser(): Flowable<ApiResponse<List<DataResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<DataResponse>>>()

        val client = apiService.getDataUser()
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                response -> val dataArray = response.items
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)

            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("remotedatasource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}



