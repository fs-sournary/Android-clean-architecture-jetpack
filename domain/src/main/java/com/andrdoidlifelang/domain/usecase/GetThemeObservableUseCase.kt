package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.SettingRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetThemeObservableUseCase(private val settingRepository: SettingRepository) :
    BaseFlowUseCase<Unit, Theme>() {

    override fun execute(params: Unit): Flow<UseCaseResult<Theme>> =
        settingRepository.getThemeObservable()
}
