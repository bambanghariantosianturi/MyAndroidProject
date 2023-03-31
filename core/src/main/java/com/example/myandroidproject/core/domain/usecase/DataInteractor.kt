package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel
import com.example.myandroidproject.core.domain.repository.IDataRepository
import javax.inject.Inject

class DataInteractor @Inject constructor(private val dataRepository: IDataRepository) :
    DataUseCase {

    override fun getAllData(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>> {
        return dataRepository.getMovieListData(page = page, genreId = genreId)
    }

    override fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>> {
        return dataRepository.getDetailMovieData(movieId)
    }

    override fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>> {
        return dataRepository.getGenreMovieData()
    }
}