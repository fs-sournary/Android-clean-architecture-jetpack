package com.andrdoidlifelang.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.andrdoidlifelang.data.ext.throwCleanException
import com.andrdoidlifelang.data.model.AuthEntity
import com.andrdoidlifelang.data.util.Constant
import com.andrdoidlifelang.domain.model.Theme
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
class AppPreferenceImpl(
    private val context: Context,
    private val gson: Gson
) : AppPreference {

    private val pref by lazy {
        context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            Constant.PREF_DARK_MODE_ENABLE -> selectedTheme.offer(getTheme())
        }
    }

    private val selectedTheme = ConflatedBroadcastChannel<String>()

    override fun setAccessToken(accessToken: String) {
        pref.edit { putString(Constant.ACCESS_TOKEN_KEY, accessToken) }
    }

    override fun getAccessToken(): String? =
        pref.getString(Constant.ACCESS_TOKEN_KEY, Constant.DEF_PREF_STRING)

    override fun setTheme(theme: String) {
        pref.edit { putString(Constant.PREF_DARK_MODE_ENABLE, theme) }
    }

    override fun getTheme(): String = pref.getString(Constant.PREF_DARK_MODE_ENABLE, null) ?: Theme.SYSTEM.storageKey

    override fun observableSelectedTheme(): Flow<String> = selectedTheme.asFlow()

    companion object {
        const val PREF_LOGIN = "pref_login"
        const val PREF_AUTH = "pref_auth"
    }

    override fun remove(key: String) {
        pref.edit {
            remove(key)
        }
    }

    override fun clear() {
        pref.edit {
            clear()
        }
    }

    override suspend fun isLogin(): Boolean = pref.getBoolean(PREF_LOGIN, false)

    override suspend fun setLogin(login: Boolean) {
        pref.edit {
            putBoolean(PREF_LOGIN, login)
        }
    }

    override suspend fun setAuth(auth: AuthEntity) {
        val authStr = gson.toJson(auth)

        pref.edit {
            putString(PREF_AUTH, authStr)
        }
    }

    override suspend fun getAuth(): AuthEntity? {
        val authStr = pref.getString(PREF_AUTH, "")

        if (authStr.isNullOrEmpty()) return null

        return runCatching {
            gson.fromJson(authStr, AuthEntity::class.java)
        }.getOrElse {
            it.throwCleanException()
            null
        }
    }

    override suspend fun clearAuth() {
        remove(PREF_AUTH)
    }
}
