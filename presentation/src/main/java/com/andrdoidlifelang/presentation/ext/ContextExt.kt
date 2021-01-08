package com.andrdoidlifelang.presentation.ext

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import com.andrdoidlifelang.data.util.Constant.PREF_LANGUAGE
import com.andrdoidlifelang.data.util.Constant.PREF_NAME
import java.util.Locale

fun Context.wrap(): ContextWrapper {
    val sharePref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val saveLocale = sharePref.getString(PREF_LANGUAGE, "")

    val config = resources.configuration
    val locale = Locale(saveLocale)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocale(locale)
        createConfigurationContext(config)
    } else {
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    return ContextWrapper(this)
}
