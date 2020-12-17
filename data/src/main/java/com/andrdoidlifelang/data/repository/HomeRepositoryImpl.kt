package com.andrdoidlifelang.data.repository

import com.andrdoidlifelang.data.net.MovieApi
import com.andrdoidlifelang.data.mapper.map
import com.andrdoidlifelang.domain.model.MainGenreType
import com.andrdoidlifelang.domain.model.Movie
import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.HomeRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : HomeRepository {

    override fun getVideos(movieId: Int): Flow<UseCaseResult<List<Video>>> = flow {
        emit(UseCaseResult.Loading)
        val videos = movieApi.getVideos(movieId).results?.map { it.map() } ?: emptyList()
        emit(UseCaseResult.Success(videos))
    }

    override fun getMainGenreMovies(type: MainGenreType, page: Int): Flow<UseCaseResult<List<Movie>>> = flow {
        emit(UseCaseResult.Loading)
        val response = when (type) {
            MainGenreType.NowPlaying -> movieApi.getNowPlayingMovies(page)
            MainGenreType.Popular -> movieApi.getPopularMovies(page)
            MainGenreType.TopRate -> movieApi.getTopRatedMovies(page)
            MainGenreType.UpComing -> movieApi.getUpcomingMovies(page)
        }
        val movies = response.results?.map { it.map() } ?: emptyList()
        emit(UseCaseResult.Success(movies))
    }
}
