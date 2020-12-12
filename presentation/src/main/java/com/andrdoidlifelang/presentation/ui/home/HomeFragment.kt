package com.andrdoidlifelang.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentHomeBinding
import com.androidlifelang.base.ext.getSystemWindowInsetEdge
import com.androidlifelang.base.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    override val layoutId: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->
            binding.statusBar.apply {
                updateLayoutParams<ConstraintLayout.LayoutParams> {
                    height = windowInsets.getSystemWindowInsetEdge().top
                }
            }
            windowInsets
        }
    }
}
