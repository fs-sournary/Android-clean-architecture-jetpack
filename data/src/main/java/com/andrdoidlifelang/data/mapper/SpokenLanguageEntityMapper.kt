package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.SpokenLanguageEntity
import com.andrdoidlifelang.domain.model.SpokenLanguage

fun SpokenLanguageEntity.map(): SpokenLanguage = SpokenLanguage(iso6391, name)
