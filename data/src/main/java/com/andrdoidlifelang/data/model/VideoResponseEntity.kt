package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class VideoResponseEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<VideoEntity>? = null
)
