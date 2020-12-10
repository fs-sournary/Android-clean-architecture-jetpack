package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.base

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
import com.andrdoidlifelang.android_clean_architecture_jetpack.BR
import com.andrdoidlifelang.android_clean_architecture_jetpack.ext.autoClear

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected val navController by lazy { findNavController() }
    protected var binding by autoClear<Binding>()

    protected abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

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
    }
}
