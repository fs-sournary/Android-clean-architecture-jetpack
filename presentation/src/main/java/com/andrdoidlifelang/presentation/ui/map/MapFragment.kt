package com.andrdoidlifelang.presentation.ui.map

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentMapBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.util.Constant
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_MAP_SCREEN_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseMainNavigationFragment<FragmentMapBinding, MapViewModel>() {

    override val viewModel by viewModels<MapViewModel>()

    override val layoutId: Int = R.layout.fragment_map

    override val screenName: String = ANALYTICS_MAP_SCREEN_NAME
}
