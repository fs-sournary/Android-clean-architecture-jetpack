package com.andrdoidlifelang.data.repository

import com.andrdoidlifelang.data.api.AuthApi
import com.andrdoidlifelang.data.ext.throwCleanException
import com.andrdoidlifelang.data.model.AuthEntityMapper
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.domain.model.Auth
import com.andrdoidlifelang.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val pref: AppPreference,
    private val authEntityMapper: AuthEntityMapper
) : AuthRepository {

    override suspend fun login(username: String, password: String): Auth? = runCatching {
        api.login(username, password).let {
            authEntityMapper.mapToDomain(it)
        }
    }.getOrElse {
        it.throwCleanException()
        null
    }

    override suspend fun signUp(username: String, password: String, email: String): Auth? = runCatching {
        api.signUp(username, password, email).let {
            authEntityMapper.mapToDomain(it)
        }
    }.getOrElse {
        it.throwCleanException()
        null
    }

    override suspend fun logout(): Boolean {
        // TODO update later
        return true
    }

    override suspend fun saveAuth(auth: Auth) {
        pref.setAuth(authEntityMapper.mapToData(auth))
    }

    override suspend fun clearAuth() {
        pref.clearAuth()
    }

    override suspend fun isLogin(): Boolean {
        return pref.getAuth() != null
    }
}
