package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Language
import com.andrdoidlifelang.domain.repository.LanguageRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetLanguageUseCase(private val languageRepository: LanguageRepository) : BaseFlowUseCase<Unit, Language>() {

    override fun execute(params: Unit): Flow<UseCaseResult<Language>> = languageRepository.getLanguage()
}
