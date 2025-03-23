package com.example.movie_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel(assistedFactory =  MovieDetailViewModelFactory::class)
class MovieDetailViewModel @AssistedInject constructor(
   @Assisted private val moovieId: Long
) : ViewModel() {

    init {
        Log.d("AAA", "id $moovieId")
    }

    private val _uiState: MutableStateFlow<MoovieDetailsUiState> = MutableStateFlow(MoovieDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: MoovieDetailsEvent){
        when(event){
            else -> {}
        }
    }

}

@AssistedFactory
interface MovieDetailViewModelFactory {
    fun create(id: Long): MovieDetailViewModel
}

sealed interface MoovieDetailsEvent{

}

sealed interface MoovieDetailsUiState{
    object Loading: MoovieDetailsUiState
}