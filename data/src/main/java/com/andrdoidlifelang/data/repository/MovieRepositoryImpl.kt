package com.andrdoidlifelang.data.repository

import com.andrdoidlifelang.data.api.MovieApi
import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.RepoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : MovieRepository {

    override fun getVideos(movieId: Int): Flow<RepoResult<List<Video>>> = flow {
    }
}
