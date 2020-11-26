package com.andrdoidlifelang.data.pref

interface AppPreference {

    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String?
}
