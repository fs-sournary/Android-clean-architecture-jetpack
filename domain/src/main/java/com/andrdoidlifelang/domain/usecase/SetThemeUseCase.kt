package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.ThemeRepository

class SetThemeUseCase(private val themeRepository: ThemeRepository) : BaseCoroutineUseCase<Theme, Unit>() {

    override suspend fun execute(params: Theme) {
        themeRepository.setTheme(params)
    }
}
