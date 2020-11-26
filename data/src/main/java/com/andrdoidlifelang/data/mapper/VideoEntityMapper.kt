package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.VideoEntity
import com.andrdoidlifelang.domain.model.Video

fun VideoEntity.map(): Video = Video(id, key, site, name, size, type)
