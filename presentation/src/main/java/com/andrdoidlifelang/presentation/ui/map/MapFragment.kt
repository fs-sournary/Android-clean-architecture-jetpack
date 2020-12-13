package com.andrdoidlifelang.presentation.ui.map

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentMapBinding
import com.andrdoidlifelang.presentation.ui.base.MainNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : MainNavigationFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel by viewModels<MapViewModel>()

    override val layoutId: Int = R.layout.fragment_map
}
