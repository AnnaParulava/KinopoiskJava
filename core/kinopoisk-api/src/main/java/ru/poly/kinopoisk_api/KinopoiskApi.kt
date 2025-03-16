@file:Suppress("unused")

package ru.poly.kinopoisk_api

import ru.poly.kinopoisk_api.models.MoviesResponse
import ru.poly.kinopoisk_api.models.MovieType
import ru.poly.kinopoisk_api.models.OrderType
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("api/v2.2/films")
    suspend fun getMovies(
        @Query("countries") countries: String? = null,
        @Query("genres") genres: String? = null,
        @Query("order") order: String = OrderType.RATING.name,
        @Query("type") type: String = MovieType.ALL.name,
        @Query("ratingFrom") ratingFrom: Double = 0.0,
        @Query("ratingTo") ratingTo: Double = 10.0,
        @Query("yearFrom") yearFrom: Int = 1000,
        @Query("yearTo") yearTo: Int = 3000,
        @Query("imdbId") imdbId: String? = null,
        @Query("keyword") keyword: String? = null,
        @Query("page") page: Int = 1
    ): Result<MoviesResponse>
}
