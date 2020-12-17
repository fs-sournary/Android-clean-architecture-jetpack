package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.MainGenreType
import com.andrdoidlifelang.domain.model.Movie
import com.andrdoidlifelang.domain.repository.HomeRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetTopRateMovieUseCase(
    private val homeRepository: HomeRepository
) : BaseFlowUseCase<Int, List<Movie>>() {

    override fun execute(params: Int): Flow<UseCaseResult<List<Movie>>> =
        homeRepository.getMainGenreMovies(MainGenreType.TopRate, params)
}
