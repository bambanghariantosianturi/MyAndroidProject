package com.example.myandroidproject.core.data.source.remote.network

import com.example.myandroidproject.core.data.source.remote.response.UserResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    //https://api.github.com/search/users?q=bambang&page=1&per_page=10&sort&order
    @GET("search/users?q=bambang&page=1&per_page=10&sort&order")
    //retrofit
//    fun getDataUser(): Call<UserResponse>

    //rx
    fun getDataUser(): Flowable<UserResponse>
}