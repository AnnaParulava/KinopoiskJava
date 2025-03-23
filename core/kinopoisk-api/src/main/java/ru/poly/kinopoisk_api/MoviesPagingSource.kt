package ru.poly.kinopoisk_api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import ru.poly.kinopoisk_api.models.Movie

class MoviesPagingSource(
    private val api: KinopoiskApi,
    private val query: String,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            delay(2000)
            val response = api.getMovies(page = page, keyword = query).getOrThrow()

            val movies = response.items.map { movieDto ->
                Movie(
                    id = movieDto.kinopoiskId,
                    title = movieDto.nameRu ?: movieDto.nameEn ?: movieDto.nameOriginal
                    ?: "Без названия",
                    originalTitle = movieDto.nameOriginal,
                    posterUrl = movieDto.posterUrl,
                    rating = movieDto.ratingKinopoisk,
                    year = movieDto.year,
                    genres = movieDto.genres.map { it.genre },
                )
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
