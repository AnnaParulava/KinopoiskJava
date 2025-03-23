package ru.poly.main_search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState.Loading
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.poly.kinopoisk_api.models.Movie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    scaffoldPaddings: PaddingValues,
    onEvent: (MainScreenEvent) -> Unit,
    uiState: StateFlow<MainScreenUiState>
) {
    val screenState = uiState.collectAsState()
    Column {
        AppBar(onEvent)

        when (val state = screenState.value) {
            MainScreenUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            MainScreenUiState.Error -> {
                Text(
                    text = "error",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is MainScreenUiState.Content -> {
                MovieList(pageData = state.films, navController = navController)
            }
        }
    }
}


@Composable
private fun MovieList(navController: NavController, pageData: Flow<PagingData<Movie>>) {
    val movies = pageData.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.itemCount) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieItem(movie = movie, onClick = {
                    Log.d("AAA", "MainScreen $movie")
                    navController.navigate("${MainScreenDestinations.DETAILS.name}/${movie.id}")
                }
                )
            }
        }
        if (movies.loadState.prepend == Loading || movies.loadState.append == Loading) {
            item { Loader() }
        }
    }
}

@Composable
private fun Loader() {
    Box(Modifier.fillMaxWidth()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}

@Composable
private fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable(onClick = onClick)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )
            movie.year?.let {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            movie.rating?.let {
                Text(
                    text = "Рейтинг: $it",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.AppBar(onEvent: (MainScreenEvent) -> Unit) {
    TopAppBar(
        title = {
            //Add title here
        },
        actions = {
            val searchExpState = remember { mutableStateOf(false) }
            val userQuery = remember { mutableStateOf(String()) }
            SearchBar(
                expanded = searchExpState.value,
                onExpandedChange = { searchExpState.value = it },
                content = {},
                inputField = {
                    TextField(
                        value = userQuery.value,
                        onValueChange = { new ->
                            userQuery.value = new
                            //here send the event via VM
                            onEvent(MainScreenEvent.UserQueryChanged(new))
                        }
                    )
                }
            )
            //
            DropDownIcon(onEvent)
        }
    )
}

@Composable
private fun DropDownIcon(onEvent: (MainScreenEvent) -> Unit) {
    val dropDownExpanded =
        remember { mutableStateOf(false) }
    Icon(
        painter = painterResource(ru.poly.core.R.drawable.baseline_search_24), //add res
        contentDescription = null,
        modifier = Modifier
            .clickable { dropDownExpanded.value = true }
            .size(26.dp),
        tint = Color.Black
    )
    DropdownMenu(
        modifier = Modifier.background(Color.White),
        expanded = dropDownExpanded.value,
        onDismissRequest = { dropDownExpanded.value = false }
    ) {
        //Here add your items in loop. Notice, here is ColumnScope
        //use onEvent to handle click like that .clickable{ onEvent(MainScreenEvent...);  dropDownExpanded.value = false}
    }
}


sealed interface MainScreenEvent {
    data class UserQueryChanged(val query: String) : MainScreenEvent
}