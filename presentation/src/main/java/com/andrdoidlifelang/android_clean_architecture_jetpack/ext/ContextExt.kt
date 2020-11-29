package com.andrdoidlifelang.android_clean_architecture_jetpack.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import androidx.core.content.ContextCompat
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.util.Constant

fun Context.openWebSite(uri: Uri) {
    if (isChromeCustomTabsSupported()) {
        CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(this, R.color.primary))
            .setSecondaryToolbarColor(ContextCompat.getColor(this, R.col))
    } else {
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}


private fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION)
    serviceIntent.setPackage(Constant.CHROME_PACKAGE)
    val info = packageManager.queryIntentServices(serviceIntent, 0)
    return info.isNullOrEmpty().not()
}
