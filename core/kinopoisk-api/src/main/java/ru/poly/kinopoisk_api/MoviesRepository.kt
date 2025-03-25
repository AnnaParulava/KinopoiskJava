package ru.poly.kinopoisk_api

import ru.poly.kinopoisk_api.models.MoviesResponse
import ru.poly.kinopoisk_api.models.MovieFilters
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: KinopoiskApi
) {
    suspend fun getMovies(filters: MovieFilters): Result<MoviesResponse> {
        return api.getMovies(
            countries = filters.countries?.joinToString(","),
            genres = filters.genres?.joinToString(","),
            order = filters.order.name,
            type = filters.type.name,
            ratingFrom = filters.ratingFrom,
            ratingTo = filters.ratingTo,
            yearFrom = filters.yearFrom,
            yearTo = filters.yearTo,
            imdbId = filters.imdbId,
            keyword = filters.keyword,
            page = filters.page
        )
    }
} 