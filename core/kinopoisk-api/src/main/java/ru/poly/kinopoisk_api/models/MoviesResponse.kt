package ru.poly.kinopoisk_api.models

data class MoviesResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<MovieDto>
)

data class MovieDto(
    val kinopoiskId: Long,
    val imdbId: String?,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int?,
    val type: String,
    val posterUrl: String,
    val posterUrlPreview: String
)

data class Country(
    val country: String
)

data class Genre(
    val genre: String
) 