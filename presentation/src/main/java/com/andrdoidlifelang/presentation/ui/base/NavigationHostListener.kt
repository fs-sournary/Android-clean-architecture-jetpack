package com.andrdoidlifelang.presentation.ui.base

import androidx.appcompat.widget.Toolbar

/**
 * This class is implemented by host navigation destinations.
 */
interface NavigationHostListener {

    /**
     * Destinations that have a Toolbar and want its Toolbar to setup with Navigation Controller.
     */
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}
