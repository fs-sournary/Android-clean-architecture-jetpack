package com.andrdoidlifelang.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCountryEntity(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)
