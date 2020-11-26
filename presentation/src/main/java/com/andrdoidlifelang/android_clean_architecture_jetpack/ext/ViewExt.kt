package com.andrdoidlifelang.android_clean_architecture_jetpack.ext

import android.view.View
import android.view.WindowInsets

fun View.showKeyboard(){
    windowInsetsController?.show(WindowInsets.Type.ime())
}
