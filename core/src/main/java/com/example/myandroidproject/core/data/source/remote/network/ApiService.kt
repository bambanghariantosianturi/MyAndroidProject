package com.example.myandroidproject.core.data.source.remote.network

import com.example.myandroidproject.core.BuildConfig
import com.example.myandroidproject.core.data.source.remote.response.DetailMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.ListMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //https://api.github.com/search/users?q=bambang&page=1&per_page=10&sort&order
    //https://api.themoviedb.org/3/discover/movie?api_key=be8b6c8aa9a5f4e240bb6093f9849051&page=1&with_genres=53
    @GET("discover/movie?api_key=be8b6c8aa9a5f4e240bb6093f9849051&page=1&with_genres=53")
    fun getMovieList(
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY,
        @Query("page") page: Int?,
        @Query("with_genres") genreId: Int?
    ): Call<ListMoviesResponse>

//    fun getUserGithub(): Call<UserResponse>

    //https://api.themoviedb.org/3/movie/497698?api_key=be8b6c8aa9a5f4e240bb6093f9849051
    @GET("movie/{movieId}?api_key=be8b6c8aa9a5f4e240bb6093f9849051")
    fun getDetailMovie(
        @Path("movieId") movieId: Int?,
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY
    ): Call<DetailMovieResponse>

    //https://api.themoviedb.org/3/genre/movie/list?api_key=be8b6c8aa9a5f4e240bb6093f9849051
    @GET("genre/movie/list?api_key=be8b6c8aa9a5f4e240bb6093f9849051")
    fun getGenreMovies(
        @Query("api_key") movieApiKey: String? = BuildConfig.API_KEY
    ): Call<GenreMovieResponse>
}