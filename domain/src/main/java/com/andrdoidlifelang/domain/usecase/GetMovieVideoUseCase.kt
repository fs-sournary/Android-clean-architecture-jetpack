package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.RepoResult
import kotlinx.coroutines.flow.Flow

class GetMovieVideoUseCase(private val movieRepository: MovieRepository) {

    fun getUseCaseFlow(movieId: Int): Flow<RepoResult<List<Video>>> = movieRepository.getVideos(movieId)
}
