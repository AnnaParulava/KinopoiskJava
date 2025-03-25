package ru.poly.kinopoisk_api.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.poly.kinopoisk_api.KinopoiskApi
import ru.poly.kinopoisk_api.MovieRepository
import ru.poly.kinopoisk_api.MoviesPagingSource
import ru.poly.kinopoisk_api.models.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi
) : MovieRepository {

    override suspend fun getMovies(query: String): Result<List<Movie>> {
        return api.getMovies(
        ).map { response ->
            response.items.map { movieDto ->
                Movie(
                    id = movieDto.kinopoiskId,
                    title = movieDto.nameRu ?: movieDto.nameEn ?: movieDto.nameOriginal
                    ?: "Без названия",
                    originalTitle = movieDto.nameOriginal,
                    posterUrl = movieDto.posterUrl,
                    rating = movieDto.ratingKinopoisk,
                    year = movieDto.year,
                    genres = movieDto.genres.map { it.genre }
                )
            }
        }
    }

    override suspend fun getMoviesPager(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(api, query) }
        ).flow
    }
} 