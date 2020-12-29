package com.andrdoidlifelang.data.pref

import com.andrdoidlifelang.data.model.AuthEntity
import kotlinx.coroutines.flow.Flow

interface AppPreference {
    /**
     * remove a preference by key
     */
    fun remove(key: String)

    /**
     * clear all preference
     */
    fun clear()

    suspend fun isLogin(): Boolean

    suspend fun setLogin(login: Boolean)

    suspend fun setAuth(auth: AuthEntity)

    suspend fun getAuth(): AuthEntity?

    suspend fun clearAuth()

    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String?

    fun setTheme(theme: String)

    fun getTheme(): String

    fun observableSelectedTheme(): Flow<String>
}
