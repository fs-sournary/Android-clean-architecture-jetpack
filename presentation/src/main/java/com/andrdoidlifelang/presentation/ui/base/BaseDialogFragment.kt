package com.andrdoidlifelang.presentation.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.andrdoidlifelang.presentation.BR
import com.andrdoidlifelang.presentation.analytics.AnalyticsHelper
import com.andrdoidlifelang.presentation.ext.autoClear
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

abstract class BaseDialogFragment<Binding : ViewDataBinding, VM : ViewModel> :
    AppCompatDialogFragment() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    abstract val screenName: String

    protected var binding by autoClear<Binding>()

    protected abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).create()
    }

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
            executePendingBindings()
        }
        if (showsDialog) {
            (requireDialog() as? AlertDialog)?.setView(binding.root)
        }
        analyticsHelper.sendScreenView(screenName, requireActivity())
    }
}
