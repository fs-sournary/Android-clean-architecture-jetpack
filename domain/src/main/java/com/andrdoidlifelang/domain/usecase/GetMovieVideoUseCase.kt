package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetMovieVideoUseCase(
    private val movieRepository: MovieRepository
) : BaseFlowUseCase<Int, List<Video>>() {

    override fun execute(params: Int): Flow<UseCaseResult<List<Video>>> = movieRepository.getVideos(params)
}
