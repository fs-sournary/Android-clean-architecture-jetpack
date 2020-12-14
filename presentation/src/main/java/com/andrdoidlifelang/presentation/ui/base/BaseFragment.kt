package com.andrdoidlifelang.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.andrdoidlifelang.data.analytics.AnalyticsHelper
import com.androidlifelang.corepresentation.ui.CoreFragment
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> :
    CoreFragment<Binding, VM>() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    abstract val screenName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsHelper.sendScreenView(screenName, requireActivity())
    }
}
