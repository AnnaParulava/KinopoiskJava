package ru.poly.kinopoisk_api.models

data class MovieFilters(
    val countries: List<Int>? = null,
    val genres: List<Int>? = null,
    val order: OrderType = OrderType.RATING,
    val type: MovieType = MovieType.ALL,
    val ratingFrom: Double = 0.0,
    val ratingTo: Double = 10.0,
    val yearFrom: Int = 1000,
    val yearTo: Int = 3000,
    val imdbId: String? = null,
    val keyword: String? = null,
    val page: Int = 1
)

enum class OrderType {
    RATING, NUM_VOTE, YEAR
}

enum class MovieType {
    FILM, TV_SHOW, TV_SERIES, MINI_SERIES, ALL
} 