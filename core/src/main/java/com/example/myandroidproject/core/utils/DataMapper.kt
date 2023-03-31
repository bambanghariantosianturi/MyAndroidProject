package com.example.myandroidproject.core.utils

import com.example.myandroidproject.core.data.source.local.entity.GenreEntity
import com.example.myandroidproject.core.data.source.remote.response.DetailMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreItemResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.MovieItemResponse
import com.example.myandroidproject.core.domain.model.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel

object DataMapper {
    object MovieListMapper {
        fun mapResponsesToDomain(input: List<MovieItemResponse>): List<MovieItemModel> {
            val dataList = ArrayList<MovieItemModel>()
            input.map {
                val data = MovieItemModel(
                    id = it.id ?: 0,
                    adult = it.adult ?: false,
                    backdrop_path = it.backdrop_path.orEmpty(),
                    genre_ids = it.genre_ids ?: listOf(),
                    original_language = it.original_language.orEmpty(),
                    original_title = it.original_title.orEmpty(),
                    overview = it.overview.orEmpty(),
                    popularity = it.popularity ?: 0.0,
                    poster_path = it.poster_path.orEmpty(),
                    release_date = it.release_date.orEmpty(),
                    title = it.title.orEmpty(),
                    video = it.video ?: false,
                    vote_average = it.vote_average ?: 0.0,
                    vote_count = it.vote_count ?: 0
                )
                dataList.add(data)
            }
            return dataList
        }
    }

    object DetailMovieMapper {
        fun mapResponseToDomain(input: DetailMovieResponse?): DetailMovie {
            return DetailMovie(
                adult = input?.adult,
                backdrop_path = input?.backdrop_path,
                budget = input?.budget,
                genres = listOf(),
                homepage = input?.homepage,
                id = input?.id,
                imdb_id = input?.imdb_id,
                original_language = input?.original_language,
                original_title = input?.original_title,
                overview = input?.overview,
                popularity = input?.popularity,
                poster_path = input?.poster_path,
                production_companies = listOf(),
                production_countries = listOf(),
                release_date = input?.release_date,
                revenue = input?.revenue,
                runtime = input?.runtime,
                spoken_languages = listOf(),
                status = input?.status,
                tagline = input?.tagline,
                title = input?.original_title,
                video = input?.video,
                vote_average = input?.vote_average,
                vote_count = input?.vote_count
            )
        }
    }

    object GenreMovie {
        fun mapResponsesToEntities(input: List<GenreItemResponse>): List<GenreEntity> {
            val dataList = ArrayList<GenreEntity>()
            input.map {
                val data = GenreEntity(
                    id = it.id ?: 0,
                    name = it.name.orEmpty()
                )
                dataList.add(data)
            }
            return dataList
        }

        fun mapEntitiesToDomain(input: List<GenreEntity>): List<GenreItemModel> {
            return input.map {
                GenreItemModel(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }
}