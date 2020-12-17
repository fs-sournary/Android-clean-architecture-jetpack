package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.HomeRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetMovieVideoUseCase(
    private val homeRepository: HomeRepository
) : BaseFlowUseCase<Int, List<Video>>() {

    override fun execute(params: Int): Flow<UseCaseResult<List<Video>>> =
        homeRepository.getVideos(params)
}
