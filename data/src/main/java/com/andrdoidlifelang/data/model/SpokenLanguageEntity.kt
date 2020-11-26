package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguageEntity(
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
)
