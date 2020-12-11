package com.androidlifelang.base.ext

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

/**
 * In the [CharSequence], we make the first instance of [boldText] bold.
 */
fun CharSequence.makeBold(boldText: String): CharSequence {
    val start = indexOf(boldText)
    if (start == -1) return this
    val end = start + boldText.length
    val span = StyleSpan(Typeface.BOLD)
    return if (this is Spannable) {
        setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this
    } else {
        val spannableString = SpannableString(this)
        spannableString.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString
    }
}
