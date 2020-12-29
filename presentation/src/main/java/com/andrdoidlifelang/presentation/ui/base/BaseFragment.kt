package com.andrdoidlifelang.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.extention.mapToExceptionItem
import com.andrdoidlifelang.presentation.extention.showAlertException
import com.andrdoidlifelang.presentation.extention.showDialogException
import com.andrdoidlifelang.presentation.extention.showSnackBarException
import com.andrdoidlifelang.presentation.model.AlertExceptionItem
import com.andrdoidlifelang.presentation.model.DialogExceptionItem
import com.andrdoidlifelang.presentation.model.SnackBarExceptionItem
import com.andrdoidlifelang.presentation.model.ToastExceptionItem
import com.andrdoidlifelang.presentation.util.analytics.AnalyticsHelper
import com.androidlifelang.corepresentation.ui.CoreFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewDataBinding, VM : BaseViewModel> :
    CoreFragment<Binding, VM>() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    abstract val screenName: String

    var loadingDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsHelper.sendScreenView(screenName, requireActivity())

        with(viewModel) {
            exceptionEvent.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let {
                    when (val e = it.mapToExceptionItem(requireContext())) {
                        is AlertExceptionItem -> {
                            showAlertException(e)
                        }

                        is SnackBarExceptionItem -> {
                            showSnackBarException(e, view)
                        }

                        is DialogExceptionItem -> {
                            showDialogException(e)
                        }

                        is ToastExceptionItem -> {
                        }
                    }
                }
            }
        }
    }

    fun showDialogLoading() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setView(R.layout.layout_loading)
        }.create().apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            loadingDialog = this

            show()
        }
    }

    fun hideDialogLoading() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }
}
