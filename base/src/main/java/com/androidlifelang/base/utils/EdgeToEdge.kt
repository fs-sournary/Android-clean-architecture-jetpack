package com.androidlifelang.base.utils

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets

/**
 * Implement edge-to-edge feature.
 * Note: on Android 30, window view flags is deprecated. Instead we should use [WindowInsets.Type].
 */
object EdgeToEdge {

    /**
     * The method configures the [rootView] and [window] of an Activity in edge-to-edge display.
     */
    @Suppress("DEPRECATION")
    fun setupRoot(rootView: View, window: Window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // laid out as if the navigation bar was hidden
            rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                // laid out fullscreen, behind navigation bar
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                // laid out at the most extreme scenario of any other flags
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else {
            // Tell the window that we want to handle/fit any system windows
            window.setDecorFitsSystemWindows(false)
        }
    }
}
