package ru.poly.main_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import ru.poly.kinopoisk_api.domain.usecase.GetMoviesUseCase
import ru.poly.kinopoisk_api.models.Movie
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()
    private val queryFlow = MutableStateFlow(String())


    init {
        viewModelScope.launch {
            queryFlow.debounce(250).flatMapLatest { query ->
                getMoviesUseCase(query)
            }
                .catch { _uiState.emit(MainScreenUiState.Error) }
                .collectLatest { movies -> _uiState.emit(MainScreenUiState.Content(movies)) }
        }
    }


    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.UserQueryChanged -> {
                setNewQuery(event.query)
            }
        }
    }

    private fun setNewQuery(query: String) {
        queryFlow.value = query
    }
}

sealed interface MainScreenUiState {
    data object Loading : MainScreenUiState
    data object Error : MainScreenUiState
    data class Content(val films: Flow<PagingData<Movie>>) : MainScreenUiState
}
