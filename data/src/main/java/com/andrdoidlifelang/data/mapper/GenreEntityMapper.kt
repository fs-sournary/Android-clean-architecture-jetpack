package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.GenreEntity
import com.andrdoidlifelang.domain.model.Genre

fun GenreEntity.map() = Genre(id, name)
