package com.andrdoidlifelang.data.pref

import android.content.Context
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.core.content.edit
import com.andrdoidlifelang.data.util.Constant
import com.andrdoidlifelang.domain.model.Theme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
@FlowPreview
class AppPreferenceImpl(context: Context) : AppPreference {

    private val pref by lazy {
        context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val changeListener = OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            Constant.PREF_DARK_MODE_ENABLE -> selectedTheme.offer(getTheme())
            Constant.PREF_LANGUAGE -> selectLanguage.offer(getLanguage())
        }
    }

    private val selectedTheme = ConflatedBroadcastChannel<String>()

    private val selectLanguage = ConflatedBroadcastChannel<String>()

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

    override fun setLanguage(language: String) {
        pref.edit { putString(Constant.PREF_LANGUAGE, language) }
    }

    override fun getLanguage(): String =
        pref.getString(Constant.PREF_LANGUAGE, Constant.DEF_PREF_STRING) ?: Constant.DEF_PREF_STRING

    override fun observableSelectLanguage(): Flow<String> = selectLanguage.asFlow()
}
