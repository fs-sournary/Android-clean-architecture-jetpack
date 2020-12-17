package com.andrdoidlifelang.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.andrdoidlifelang.presentation.BR
import com.andrdoidlifelang.presentation.analytics.AnalyticsHelper
import com.andrdoidlifelang.presentation.ext.autoClear
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : Fragment() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    protected val navController by lazy { findNavController() }
    protected var binding by autoClear<Binding>()

    protected abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val screenName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
        analyticsHelper.sendScreenView(screenName, requireActivity())
    }
}
