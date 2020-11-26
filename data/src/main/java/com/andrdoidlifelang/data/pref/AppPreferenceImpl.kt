package com.andrdoidlifelang.data.pref

import android.content.Context
import androidx.core.content.edit
import com.andrdoidlifelang.data.util.Constant

class AppPreferenceImpl(context: Context) : AppPreference {

    private val pref = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)

    override fun setAccessToken(accessToken: String) {
        pref.edit { putString(Constant.ACCESS_TOKEN_KEY, accessToken) }
    }

    override fun getAccessToken(): String? =
        pref.getString(Constant.ACCESS_TOKEN_KEY, Constant.DEF_PREF_STRING)
}
