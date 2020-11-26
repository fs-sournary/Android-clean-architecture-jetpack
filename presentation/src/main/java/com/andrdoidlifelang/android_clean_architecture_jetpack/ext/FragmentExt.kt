package com.andrdoidlifelang.android_clean_architecture_jetpack.ext

import androidx.fragment.app.Fragment
import com.andrdoidlifelang.android_clean_architecture_jetpack.util.AutoClearedValue

fun <T : Any> Fragment.autoClear(): AutoClearedValue<T> = AutoClearedValue(this)
