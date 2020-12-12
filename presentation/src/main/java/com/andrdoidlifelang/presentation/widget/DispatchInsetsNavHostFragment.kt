package com.andrdoidlifelang.presentation.widget

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.navigation.fragment.NavHostFragment

/**
 * By default, material layout such as DrawerLayout, CoordinatorLayout...will automatically dispatch
 * insets to their child. But standard layouts such as RelativeLayout, LinearLayout, FrameLayout...
 * are not.
 */
class DispatchInsetsNavHostFragment : NavHostFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnApplyWindowInsetsListener { v, insets ->
            (v as? ViewGroup)?.forEach {
                it.dispatchApplyWindowInsets(insets)
            }
            insets
        }
    }
}
