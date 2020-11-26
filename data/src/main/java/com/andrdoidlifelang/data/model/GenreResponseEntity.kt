package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponseEntity(
    @SerializedName("genres")
    val genres: List<GenreEntity>? = null
)
