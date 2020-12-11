package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.map

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.FragmentMapBinding
import com.andrdoidlifelang.android_clean_architecture_jetpack.ui.base.BaseFragment

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel by viewModels<MapViewModel>()

    override val layoutId: Int = R.layout.fragment_map
}
