package com.example.myandroidproject.core.domain.model

data class DetailMovie(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val budget: Int? = null,
    val genres: List<Genre> = listOf(),
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String? = null,
    val revenue: Double? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)