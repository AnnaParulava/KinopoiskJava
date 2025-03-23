package ru.poly.kinopoisk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.poly.kinopoisk.ui.theme.KinopoiskTheme
import ru.poly.main_search.MainScreenDestinations
import ru.poly.main_search.deployMainSearch
import ru.poly.main_search.deployMovieDetails

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinopoiskTheme {
                Host()
            }
        }
    }
}


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun Host() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomBarVisibility = remember { mutableStateOf(false) }

    Scaffold(bottomBar = {
        //TODO Implement if you need bottom bar
    }) { scaffoldPaddings ->
        MainScreen(navController, scaffoldPaddings)
    }
}

@Composable
fun MainScreen(navController: NavHostController, scaffoldPaddings: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = RootDestinations.KINO_LIST.name
    ) {
        navigation(
            route = RootDestinations.KINO_LIST.name,
            startDestination = MainScreenDestinations.SEARCH_MAIN.name
        ) {
            deployMainSearch(navController, scaffoldPaddings)
            deployMovieDetails(navController, PaddingValues())
        }
    }
}

enum class RootDestinations {
    KINO_LIST
}