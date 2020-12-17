package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.MainGenreType
import com.andrdoidlifelang.domain.model.Movie
import com.andrdoidlifelang.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getVideos(movieId: Int): Flow<UseCaseResult<List<Video>>>

    fun getMainGenreMovies(type: MainGenreType, page: Int): Flow<UseCaseResult<List<Movie>>>
}
