package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.model.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel

interface DataUseCase {

    fun getAllData(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>>

    fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>>

    fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>>
}