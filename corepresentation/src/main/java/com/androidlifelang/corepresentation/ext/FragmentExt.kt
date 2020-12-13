package com.androidlifelang.corepresentation.ext

import androidx.fragment.app.Fragment
import com.androidlifelang.corepresentation.utils.AutoClearedValue

/**
 * Create a wrapper for properties that will be clear when its fragment' View is destroyed.
 */
fun <T : Any> Fragment.autoClear(): AutoClearedValue<T> = AutoClearedValue(this)
