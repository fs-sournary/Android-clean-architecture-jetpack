package com.andrdoidlifelang.presentation.ext

import android.view.View

/**
 * Request window insets by using [View.requestApplyInsets]. if View has already attached to Window,
 * we request window insets immediately. Otherwise, we listen to [View.OnAttachStateChangeListener]
 * to wait it attach to Window before calling [View.requestApplyInsets].
 */
fun View.requestInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(v: View) = Unit

            override fun onViewAttachedToWindow(v: View) {
                v.requestApplyInsets()
            }
        })
    }
}
