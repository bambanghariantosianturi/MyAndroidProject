package com.example.myandroidproject.list

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel

interface IListMovieViewModel {

    fun getMovieList(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>>
}