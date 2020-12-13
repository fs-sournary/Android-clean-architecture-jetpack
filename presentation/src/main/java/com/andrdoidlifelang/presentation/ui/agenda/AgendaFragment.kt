package com.andrdoidlifelang.presentation.ui.agenda

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentAgendaBinding
import com.andrdoidlifelang.presentation.ui.base.MainNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgendaFragment : MainNavigationFragment<FragmentAgendaBinding, AgendaViewModel>() {

    override val viewModel: AgendaViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_agenda
}
