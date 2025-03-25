package ru.poly.kinopoisk_api.models

data class Movie(
    val id: Long,
    val title: String,
    val originalTitle: String?,
    val posterUrl: String,
    val rating: Double?,
    val year: Int?,
    val genres: List<String>,
) 