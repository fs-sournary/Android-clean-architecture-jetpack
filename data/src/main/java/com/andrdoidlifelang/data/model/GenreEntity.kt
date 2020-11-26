package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)
