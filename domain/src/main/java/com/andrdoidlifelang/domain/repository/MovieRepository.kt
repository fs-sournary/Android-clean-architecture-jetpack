package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getVideos(movieId: Int): Flow<RepoResult<List<Video>>>
}
