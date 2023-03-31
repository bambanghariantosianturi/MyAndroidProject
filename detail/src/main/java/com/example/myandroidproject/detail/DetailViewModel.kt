package com.example.myandroidproject.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.DetailMovie
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val dataUseCase: DataUseCase): ViewModel(), IDetailViewModel {

    override fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>> {
        return dataUseCase.getDetailMovieData(movieId)
    }
}