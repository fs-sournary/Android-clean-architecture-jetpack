package com.andrdoidlifelang.data.pref

import kotlinx.coroutines.flow.Flow

interface AppPreference {

    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String?

    fun setTheme(theme: String)

    fun getTheme(): String

    fun observableSelectedTheme(): Flow<String>

    fun setLanguage(language: String)

    fun getLanguage(): String

    fun observableSelectLanguage(): Flow<String>
}
