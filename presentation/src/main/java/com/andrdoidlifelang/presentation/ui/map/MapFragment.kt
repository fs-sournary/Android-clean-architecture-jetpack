package com.andrdoidlifelang.presentation.ui.map

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentMapBinding
import com.androidlifelang.base.ui.BaseFragment

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel by viewModels<MapViewModel>()

    override val layoutId: Int = R.layout.fragment_map
}
