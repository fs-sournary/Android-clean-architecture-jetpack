package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.MainGenreType
import com.andrdoidlifelang.domain.model.Movie
import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetUpcomingMovieUseCase(
    private val movieRepository: MovieRepository
) : BaseFlowUseCase<Int, List<Movie>>() {

    override fun execute(params: Int): Flow<UseCaseResult<List<Movie>>> =
        movieRepository.getMainGenreMovies(MainGenreType.UpComing, params)
}
