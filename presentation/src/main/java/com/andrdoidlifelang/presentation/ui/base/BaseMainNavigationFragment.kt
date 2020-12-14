package com.andrdoidlifelang.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.updateLayoutParams
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.andrdoidlifelang.presentation.R
import com.androidlifelang.corepresentation.ext.autoClear
import com.androidlifelang.corepresentation.ext.getSystemWindowInsetEdge

/**
 * Base class for fragments in main. This class handles setting up [Toolbar] with navigation icon.
 */
abstract class BaseMainNavigationFragment<Binding : ViewDataBinding, VM : ViewModel> :
    BaseFragment<Binding, VM>() {

    private var navHostListener by autoClear<NavigationHostListener>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHostListener) {
            navHostListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val statusBar = view.findViewById<View>(R.id.status_bar)
        binding.root.setOnApplyWindowInsetsListener { _, insets ->
            statusBar.updateLayoutParams<ViewGroup.LayoutParams> {
                height = insets.getSystemWindowInsetEdge().top
            }
            insets
        }
        // Toolbar with navigation
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar) ?: return
        navHostListener.registerToolbarWithNavigation(toolbar)
    }
}
