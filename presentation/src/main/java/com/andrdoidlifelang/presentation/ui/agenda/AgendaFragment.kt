package com.andrdoidlifelang.presentation.ui.agenda

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentAgendaBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgendaFragment : BaseMainNavigationFragment<FragmentAgendaBinding, AgendaViewModel>() {

    override val viewModel: AgendaViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_agenda

    override val screenName: String = Constant.ANALYTICS_AGENDA_SCREEN_NAME
}
