package com.andrdoidlifelang.presentation.mapper

import com.andrdoidlifelang.domain.model.Video
import com.andrdoidlifelang.domain.repository.UseCaseResult
import com.andrdoidlifelang.domain.repository.data
import com.andrdoidlifelang.presentation.model.VideoUi

fun UseCaseResult<List<Video>>.map(): UseCaseResult<List<VideoUi>> {
    val videoUis = this.data?.map { it.map() } ?: emptyList()
    return UseCaseResult.Success(videoUis)
}

fun Video.map(): VideoUi = VideoUi(id, key, site, name, size, type)
