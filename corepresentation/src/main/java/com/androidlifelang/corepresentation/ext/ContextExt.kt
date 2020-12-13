package com.androidlifelang.corepresentation.ext

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import com.androidlifelang.corepresentation.R

fun Context.createNavigationDrawerItemBackground(): Drawable? {
    var background = AppCompatResources.getDrawable(this, R.drawable.bg_nav_drawer_item)
    if (background != null) {
        val tint = AppCompatResources.getColorStateList(this, R.color.bg_tint_nav_drawer_item)
        background = DrawableCompat.wrap(background.mutate())
        background.setTintList(tint)
    }
    return background
}
