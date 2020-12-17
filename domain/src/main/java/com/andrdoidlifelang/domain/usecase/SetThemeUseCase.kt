package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.SettingRepository

class SetThemeUseCase(
    private val settingRepository: SettingRepository
) : BaseCoroutineUseCase<Theme, Unit>() {

    override suspend fun execute(params: Theme) {
        settingRepository.setTheme(params)
    }
}
