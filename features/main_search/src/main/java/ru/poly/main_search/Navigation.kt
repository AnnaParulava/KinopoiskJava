package ru.poly.main_search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.deployMainSearch(
    navController: NavController,
    scaffoldPaddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    mainScreen(navController, scaffoldPaddings, modifier)
}


fun NavGraphBuilder.mainScreen(navController: NavController, scaffoldPaddings: PaddingValues, modifier: Modifier) {
    composable(route = MainScreenDestinations.SEARCH_MAIN.name) {
        val vm: MainScreenViewModel = MainScreenViewModel()
        MainScreen(navController, scaffoldPaddings, vm::onEvent, vm.uiState)
    }
}


enum class MainScreenDestinations {
    SEARCH_MAIN, DETAILS
}
