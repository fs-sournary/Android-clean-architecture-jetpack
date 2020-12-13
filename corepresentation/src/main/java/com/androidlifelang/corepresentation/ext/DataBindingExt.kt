package com.androidlifelang.corepresentation.ext

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

@BindingAdapter("visibleGone")
fun View.setVisibleGone(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.setImageUrl(imageUrl: String?, placeholder: Drawable?) {
    setImageUri(imageUrl?.toUri(), placeholder)
}

@BindingAdapter(value = ["imageUri", "placeholder"], requireAll = false)
fun ImageView.setImageUri(imageUri: Uri?, placeholder: Drawable?) {
    when (imageUri) {
        null -> {
            Timber.d("Image url is not set")
            Glide.with(this).load(placeholder).into(this)
        }
        else -> {
            Glide.with(this)
                .load(imageUri)
                .apply(RequestOptions().placeholder(placeholder))
                .into(this)
        }
    }
}
