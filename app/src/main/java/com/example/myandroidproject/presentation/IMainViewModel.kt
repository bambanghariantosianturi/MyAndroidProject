package com.example.myandroidproject.presentation

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel

interface IMainViewModel {

//    fun setName(name: String)

    fun getAllData(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>>

    fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>>
}