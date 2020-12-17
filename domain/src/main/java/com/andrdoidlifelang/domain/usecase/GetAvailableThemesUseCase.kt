package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.ThemeRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetAvailableThemesUseCase(private val themeRepository: ThemeRepository) : BaseFlowUseCase<Unit, List<Theme>>() {

    override fun execute(params: Unit): Flow<UseCaseResult<List<Theme>>> = themeRepository.getListTheme()
}
