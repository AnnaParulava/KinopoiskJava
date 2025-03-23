package ru.poly.main_search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movie_detail.MovieDetailViewModel
import com.example.movie_detail.MovieDetailViewModelFactory

fun NavGraphBuilder.deployMainSearch(
    navController: NavController,
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    mainScreen(navController, scaffoldPaddings, modifier)
}

fun NavGraphBuilder.deployMovieDetails(
    navController: NavController,
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    movieDetail(navController, scaffoldPaddings, modifier)
}


fun NavGraphBuilder.mainScreen( navController: NavController, scaffoldPaddings: PaddingValues, modifier: Modifier) {
    composable(route = MainScreenDestinations.SEARCH_MAIN.name) {
        val viewModel: MainScreenViewModel = hiltViewModel()
        MainScreen(navController, scaffoldPaddings, viewModel::onEvent, viewModel.uiState)
    }
}

fun NavGraphBuilder.movieDetail ( navController: NavController, scaffoldPaddings: PaddingValues, modifier: Modifier) {
    composable(route = "${MainScreenDestinations.DETAILS.name}/{movieId}") { navBackStackEntry ->
        val movieId = navBackStackEntry.arguments?.getString("movieId")?.toLong() ?: error("Args should be passed")
        val vm: MovieDetailViewModel = hiltViewModel<MovieDetailViewModel, MovieDetailViewModelFactory>{ factory ->
            factory.create(movieId)
        } //Send arg here instead of composable above
        MovieDetailScreen(vm.uiState, vm::handleEvent, navController)

    }
}

enum class MainScreenDestinations {
    SEARCH_MAIN, DETAILS
}
