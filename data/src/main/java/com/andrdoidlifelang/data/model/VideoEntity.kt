package com.andrdoidlifelang.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("site")
    val site: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("size")
    val size: Int? = null,
    @SerializedName("type")
    val type: String? = null
)
