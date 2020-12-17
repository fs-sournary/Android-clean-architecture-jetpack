package com.andrdoidlifelang.presentation.ext

import android.os.Build
import android.view.WindowInsets
import com.andrdoidlifelang.presentation.model.SystemWindowInsetEdge

/**
 * Get system window (status bar, navigation bar, ime...) insets base on API level.
 */
@Suppress("DEPRECATION")
fun WindowInsets.getSystemWindowInsetEdge(): SystemWindowInsetEdge {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        SystemWindowInsetEdge(
            left = systemWindowInsetLeft,
            top = systemWindowInsetTop,
            right = systemWindowInsetRight,
            bottom = systemWindowInsetBottom
        )
    } else {
        val systemWindowInsets = getInsets(
            WindowInsets.Type.systemBars() or WindowInsets.Type.ime()
        )
        SystemWindowInsetEdge(
            left = systemWindowInsets.left,
            top = systemWindowInsets.top,
            right = systemWindowInsets.right,
            bottom = systemWindowInsets.bottom
        )
    }
}
