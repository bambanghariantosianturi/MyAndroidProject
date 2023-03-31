package com.example.myandroidproject.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.myandroidproject.core.data.source.local.LocalDataSource
import com.example.myandroidproject.core.data.source.remote.RemoteDataSource
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreItemResponse
import com.example.myandroidproject.core.domain.model.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel
import com.example.myandroidproject.core.domain.repository.IDataRepository
import com.example.myandroidproject.core.utils.AppExecutors
import com.example.myandroidproject.core.utils.DataMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDataRepository {

    override fun getMovieListData(
        page: Int,
        genreId: Int
    ): LiveData<Resource<List<MovieItemModel>>> {
        val result = MediatorLiveData<Resource<List<MovieItemModel>>>()

        result.addSource(remoteDataSource.getListMovies(page, genreId)) { response ->
            when (response) {
                is ApiResponse.Success ->
                    appExecutors.diskIO().execute {
                        appExecutors.mainThread().execute {
                            result.value = Resource.Success(
                                DataMapper.MovieListMapper.mapResponsesToDomain(response.data)
                            )
                        }
                    }
                is ApiResponse.Empty -> appExecutors.mainThread().execute {
                    result.value = Resource.Success(
                        DataMapper.MovieListMapper.mapResponsesToDomain(
                            listOf()
                        )
                    )
                }
                is ApiResponse.Error -> {
                    result.value = Resource.Error(response.errorMessage, listOf())
                }
            }

        }
        return result
    }

    override fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>> {
        val result = MediatorLiveData<Resource<DetailMovie>>()

        result.addSource(remoteDataSource.getDetailMovie(movieId)) { newData ->
            result.value = Resource.Loading(
                DataMapper.DetailMovieMapper.mapResponseToDomain(null)
            )
        }

        result.addSource(remoteDataSource.getDetailMovie(movieId)) { response ->
            when (response) {
                is ApiResponse.Success ->
                    appExecutors.diskIO().execute {
                        appExecutors.mainThread().execute {
                            result.value = Resource.Success(
                                DataMapper.DetailMovieMapper.mapResponseToDomain(response.data)
                            )
                        }
                    }
                is ApiResponse.Empty -> appExecutors.mainThread().execute {
                    result.value = Resource.Success(
                        DataMapper.DetailMovieMapper.mapResponseToDomain(
                            null
                        )
                    )
                }
                is ApiResponse.Error -> {
                    result.value = Resource.Error(response.errorMessage, null)
                }
            }

        }
        return result
    }

    override fun getGenreMovieData(): LiveData<Resource<List<GenreItemModel>>> {
        return object :
            NetworkBoundResource<List<GenreItemModel>, List<GenreItemResponse>>(appExecutors) {
            override fun onFetchFailed() {
                super.onFetchFailed()
            }

            override fun loadFromDB(): LiveData<List<GenreItemModel>> {
                return Transformations.map(localDataSource.getGenreMovie()) {
                    DataMapper.GenreMovie.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GenreItemModel>?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<List<GenreItemResponse>>> {
                return remoteDataSource.getGenreMovie()
            }

            override fun saveCallResult(data: List<GenreItemResponse>) {
                val genreData = DataMapper.GenreMovie.mapResponsesToEntities(data)
                localDataSource.insertGenreData(genreData)
            }
        }.asLiveData()
    }
}