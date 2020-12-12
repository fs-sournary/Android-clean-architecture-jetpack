package com.andrdoidlifelang.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Window
import android.view.WindowInsets
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.andrdoidlifelang.presentation.R

/**
 * A custom [FrameLayout] which will draw a divider on the edge of the navigation bar. It provides
 * the sames functions to [Window.setNavigationBarDividerColor] but it also provides work with
 * translucent navigation bar colors.
 */
class NavigationBarContentFrameLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    var navigationBarDividerColor: Int = 0
        set(value) {
            field = value
            dividerDrawable.color = value
        }

    var navigationBarDividerSize: Int = 0
        set(value) {
            field = value
            updateDividerBounds()
        }

    private var lastInsets: WindowInsets? = null

    private val dividerDrawable by lazy(LazyThreadSafetyMode.NONE) {
        ColorDrawable(navigationBarDividerColor).apply {
            callback = this@NavigationBarContentFrameLayout
            alpha = 255
        }
    }

    init {
        context.withStyledAttributes(
            attributeSet,
            R.styleable.NavigationBarContentFrameLayout,
            defStyleAttr,
            0
        ) {
            navigationBarDividerColor = getColor(
                R.styleable.NavigationBarContentFrameLayout_navigationBarBorderColor,
                Color.MAGENTA
            )
            navigationBarDividerSize = getDimensionPixelSize(
                R.styleable.NavigationBarContentFrameLayout_navigationBarBorderSize,
                0
            )
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateDividerBounds()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        if (dividerDrawable.bounds.isEmpty.not()) {
            dividerDrawable.draw(canvas)
        }
    }

    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        lastInsets = insets
        updateDividerBounds()
        return super.onApplyWindowInsets(insets)
    }

    @Suppress("DEPRECATION")
    private fun updateDividerBounds() {
        val leftInset: Int
        val rightInset: Int
        val bottomInset: Int
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            leftInset = lastInsets?.systemWindowInsetLeft ?: 0
            rightInset = lastInsets?.systemWindowInsetRight ?: 0
            bottomInset = lastInsets?.systemWindowInsetBottom ?: 0
        } else {
            val systemWindow = lastInsets?.getInsets(
                WindowInsets.Type.systemBars() or WindowInsets.Type.ime()
            ) ?: return
            leftInset = systemWindow.left
            rightInset = systemWindow.right
            bottomInset = systemWindow.bottom
        }
        when {
            bottomInset > 0 -> dividerDrawable.setBounds(
                left,
                bottom - bottomInset,
                right,
                bottom - bottomInset + navigationBarDividerSize
            )
            leftInset > 0 -> dividerDrawable.setBounds(
                leftInset - navigationBarDividerSize,
                top,
                leftInset,
                bottom
            )
            rightInset > 0 -> dividerDrawable.setBounds(
                right - rightInset,
                top,
                right - rightInset + navigationBarDividerSize,
                bottom
            )
            else -> dividerDrawable.setBounds(0, 0, 0, 0)
        }
        // Optimize if the view doesn't do any drawing on its own.
        setWillNotDraw(dividerDrawable.bounds.isEmpty)
    }
}
