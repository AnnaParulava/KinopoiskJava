package ru.poly.kinopoisk_api.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.poly.kinopoisk_api.MovieRepository
import ru.poly.kinopoisk_api.models.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(query: String = ""): Flow<Flow<PagingData<Movie>>> = flow {
        emit(repository.getMoviesPager(query))
    }
}
