package com.example.myandroidproject.detail

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.DetailMovie

interface IDetailViewModel {

    fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>>
}