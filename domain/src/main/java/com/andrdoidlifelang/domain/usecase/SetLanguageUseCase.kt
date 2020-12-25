package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.Language
import com.andrdoidlifelang.domain.repository.LanguageRepository

class SetLanguageUseCase(private val languageRepository: LanguageRepository) : BaseCoroutineUseCase<Language, Unit>() {

    override suspend fun execute(params: Language) {
        languageRepository.setLanguage(params)
    }
}
