package com.andrdoidlifelang.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.withTranslation
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.andrdoidlifelang.presentation.R
import kotlin.math.max
import kotlin.math.roundToInt

class MovieHashTagItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var drawable: Drawable? = null
    private var margin: Int = 0

    private var decorBottom = 0

    init {
        context.withStyledAttributes(
            R.style.Widget_AndroidACleanArchitectureJetpack_MovieHashTagItemDecoration,
            R.styleable.MovieHashTagItemDecoration
        ) {
            drawable = getDrawable(R.styleable.MovieHashTagItemDecoration_android_drawable)?.apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }
            margin = getDimensionPixelOffset(R.styleable.MovieHashTagItemDecoration_margin, 0)
        }
        decorBottom = 2 * margin + (drawable?.intrinsicHeight ?: 0)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (drawable == null || parent.getChildAdapterPosition(view) != state.itemCount - 1) {
            super.getItemOffsets(outRect, view, parent, state)
        } else {
            outRect.set(0, 0, 0, decorBottom)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val d = drawable ?: return
        val x = parent.width - parent.paddingEnd - margin - d.intrinsicWidth
        val yFromParentBottom = parent.height - parent.paddingBottom - margin - d.intrinsicHeight
        if (state.itemCount < 1) {
            // Recycler has no children. Just draw at the bottom of it
            c.withTranslation(x.toFloat(), yFromParentBottom.toFloat()) { d.draw(c) }
        }
        // Find the decor View
        val child = findTargetChild(parent, state.itemCount - 1) ?: return
        val yFromChildBottom = child.bottom + child.translationY.roundToInt() + margin
        if (yFromChildBottom > parent.height) {
            // No more space to layout the decoration
            return
        }
        val y = max(yFromChildBottom, yFromParentBottom)
        c.withTranslation(x.toFloat(), y.toFloat()) { d.draw(c) }
    }

    private fun findTargetChild(parent: RecyclerView, position: Int): View? {
        parent.forEach {
            if (parent.getChildAdapterPosition(it) == position) {
                return it
            }
        }
        return null
    }
}
