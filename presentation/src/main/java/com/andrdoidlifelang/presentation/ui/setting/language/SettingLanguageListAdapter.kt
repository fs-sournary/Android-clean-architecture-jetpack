package com.andrdoidlifelang.presentation.ui.setting.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrdoidlifelang.presentation.databinding.ItemSettingLanguageListBinding
import com.andrdoidlifelang.presentation.model.LanguageHolderUi
import com.androidlifelang.corepresentation.ext.executeAfter

class SettingLanguageListAdapter(
    private val settingLanguageViewModel: SettingLanguageViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<LanguageHolderUi, SettingLanguageViewHolder>(FilterLanguageDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingLanguageViewHolder =
        SettingLanguageViewHolder(
            ItemSettingLanguageListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            settingLanguageViewModel,
            lifecycleOwner
        )

    override fun onBindViewHolder(holder: SettingLanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SettingLanguageViewHolder(
    private val binding: ItemSettingLanguageListBinding,
    private val settingLanguageViewModel: SettingLanguageViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(languageUi: LanguageHolderUi) {
        binding.executeAfter {
            languageName = languageUi
            viewModel = settingLanguageViewModel
            lifecycleOwner = this@SettingLanguageViewHolder.lifecycleOwner
        }
    }
}

private object FilterLanguageDiff : DiffUtil.ItemCallback<LanguageHolderUi>() {

    override fun areItemsTheSame(oldItem: LanguageHolderUi, newItem: LanguageHolderUi): Boolean =
        oldItem.language == newItem.language

    override fun areContentsTheSame(oldItem: LanguageHolderUi, newItem: LanguageHolderUi): Boolean = oldItem == newItem
}
