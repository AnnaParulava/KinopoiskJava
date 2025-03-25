package ru.poly.kinopoisk_api

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.poly.kinopoisk_api.models.Movie


interface MovieRepository {
    suspend fun getMovies(query: String): Result<List<Movie>>
    suspend fun getMoviesPager(query: String): Flow<PagingData<Movie>>
}