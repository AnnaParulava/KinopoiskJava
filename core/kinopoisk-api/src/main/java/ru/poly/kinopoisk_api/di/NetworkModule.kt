package ru.poly.kinopoisk_api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.poly.kinopoisk_api.BuildConfig
import ru.poly.kinopoisk_api.BuildConfig.KINOPOISK_API_KEY
import ru.poly.kinopoisk_api.KinopoiskApi
import ru.poly.kinopoisk_api.KinopoiskApiFactory
import ru.poly.kinopoisk_api.MoviesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideKinopoiskApi(): KinopoiskApi {
        return KinopoiskApiFactory.create(KINOPOISK_API_KEY)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: KinopoiskApi): MoviesRepository {
        return MoviesRepository(api)
    }
} 