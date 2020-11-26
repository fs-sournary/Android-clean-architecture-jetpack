package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.MovieEntity
import com.andrdoidlifelang.domain.model.Movie

fun MovieEntity.map(): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genres = genres?.map { it.map() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCountries = productionCountries?.map { it.map() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.map() },
    status = status,
    tagLine = tagLine,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
