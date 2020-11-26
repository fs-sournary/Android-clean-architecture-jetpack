package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponseEntity(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieEntity>? = null,
    @SerializedName("total_pages")
    val totalPage: Int? = null,
    @SerializedName("total_results")
    val totalResult: Int? = null
)
