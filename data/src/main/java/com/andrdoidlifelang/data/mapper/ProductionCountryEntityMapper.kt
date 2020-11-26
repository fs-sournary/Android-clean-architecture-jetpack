package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.ProductionCountryEntity
import com.andrdoidlifelang.domain.model.ProductionCountry

fun ProductionCountryEntity.map(): ProductionCountry = ProductionCountry(iso31661, name)
