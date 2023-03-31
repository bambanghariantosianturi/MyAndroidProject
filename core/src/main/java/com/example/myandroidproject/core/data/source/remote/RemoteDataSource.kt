package com.example.myandroidproject.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.network.ApiService
import com.example.myandroidproject.core.data.source.remote.response.DetailMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreItemResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.ListMoviesResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.MovieItemResponse
//import com.example.myandroidproject.core.utils.JsonHelper
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
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(service: ApiService): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(service)
//            }
//    }

    fun getListMovies(page: Int, genreId: Int): LiveData<ApiResponse<List<MovieItemResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieItemResponse>>>()

        val client = apiService.getMovieList(page = page, genreId = genreId)

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

        client.enqueue(object : Callback<ListMoviesResponse?> {
            override fun onResponse(
                call: Call<ListMoviesResponse?>,
                response: Response<ListMoviesResponse?>
            ) {
                val dataResponse = response.body()?.movieItemResponses
                resultData.value = if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("remote success", response.body().toString())
            }

            override fun onFailure(call: Call<ListMoviesResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("remote error", t.message.toString())
            }
        })
        return resultData
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val resultData = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        val client = apiService.getDetailMovie(movieId = movieId)
        client.enqueue(object : Callback<DetailMovieResponse?> {
            override fun onResponse(
                call: Call<DetailMovieResponse?>,
                response: Response<DetailMovieResponse?>
            ) {
                val dataResponse = response.body()
                resultData.value = if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("detail response success", response.body().toString())
            }

            override fun onFailure(call: Call<DetailMovieResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("detail response error", t.message.toString())
            }
        })
        return resultData
    }

    fun getGenreMovie(): LiveData<ApiResponse<List<GenreItemResponse>>> {
        val result = MutableLiveData<ApiResponse<List<GenreItemResponse>>>()

        val client = apiService.getGenreMovies()
        client.enqueue(object : Callback<GenreMovieResponse?> {
            override fun onResponse(
                call: Call<GenreMovieResponse?>,
                response: Response<GenreMovieResponse?>
            ) {
                val dataResponse = response.body()?.genreItemResponses
                result.value = if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("genre response success", response.body().toString())
            }

            override fun onFailure(call: Call<GenreMovieResponse?>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
                Log.d("genre response error", t.message.toString())
            }
        })
        return result
    }
}



