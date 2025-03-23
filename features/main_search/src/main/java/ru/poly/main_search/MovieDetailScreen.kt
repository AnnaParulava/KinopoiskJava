package ru.poly.main_search

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.movie_detail.MoovieDetailsEvent
import com.example.movie_detail.MoovieDetailsUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailScreen(
     uiStateFlow: StateFlow<MoovieDetailsUiState>,
     eventReceiver: (MoovieDetailsEvent) -> Unit,
     navController: NavController
) {
    val uiState = uiStateFlow.collectAsState()

    Log.d("AAA", "state ")


    when (val state = uiState.value) {
        MoovieDetailsUiState.Loading -> {}
    }
}
