package com.andrdoidlifelang.presentation.ui.setting.language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.DialogSettingLanguageBinding
import com.andrdoidlifelang.presentation.model.LanguageHolderUi
import com.andrdoidlifelang.presentation.ui.base.BaseDialogFragment
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_SETTING_LANGUAGE_SCREEN_NAME
import com.androidlifelang.corepresentation.ext.autoClear
import com.androidlifelang.corepresentation.model.LanguageUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingLanguageFragment : BaseDialogFragment<DialogSettingLanguageBinding, SettingLanguageViewModel>() {

    override val screenName = ANALYTICS_SETTING_LANGUAGE_SCREEN_NAME

    override val viewModel: SettingLanguageViewModel by viewModels()

    override val layoutId = R.layout.dialog_setting_language

    private var adapter by autoClear<SettingLanguageListAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setupAdapter()
    }

    private fun bindViewModel() {
        viewModel.apply {
            availableLanguage.observe(viewLifecycleOwner, ::setListAdapter)

            clickItem.observe(viewLifecycleOwner) {
                dismiss()
                activity?.recreate()
            }
        }
    }

    private fun setupAdapter() {
        adapter = SettingLanguageListAdapter(viewModel, viewLifecycleOwner)
        binding.rvLanguage.adapter = adapter
    }

    private fun setListAdapter(list: List<LanguageUi>) {
        adapter.submitList(list.map { item -> LanguageHolderUi(item, getTitleForLanguage(item)) })
    }

    private fun getTitleForLanguage(language: LanguageUi): String = getString(
        when (language) {
            LanguageUi.EN -> R.string.setting_language_en
            LanguageUi.VI -> R.string.setting_language_vi
        }
    )
}
