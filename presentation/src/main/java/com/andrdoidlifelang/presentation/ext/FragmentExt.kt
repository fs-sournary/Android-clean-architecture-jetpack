package com.andrdoidlifelang.presentation.ext

import androidx.fragment.app.Fragment
import com.andrdoidlifelang.presentation.util.AutoClearedValue

/**
 * Create a wrapper for properties that will be clear when its fragment' View is destroyed.
 */
fun <T : Any> Fragment.autoClear(): AutoClearedValue<T> = AutoClearedValue(this)
