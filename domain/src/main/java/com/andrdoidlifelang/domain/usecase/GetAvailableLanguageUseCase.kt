package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Language
import com.andrdoidlifelang.domain.repository.LanguageRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow

class GetAvailableLanguageUseCase(private val languageRepository: LanguageRepository) :
    BaseFlowUseCase<Unit, List<Language>>() {

    override fun execute(params: Unit): Flow<UseCaseResult<List<Language>>> = languageRepository.getListLanguage()
}
