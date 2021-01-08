package com.andrdoidlifelang.presentation.ui.setting.detailsetting

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.andrdoidlifelang.presentation.BuildConfig
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.util.EventObserver
import com.androidlifelang.corepresentation.model.LanguageUi
import com.androidlifelang.corepresentation.model.LanguageUi.EN
import com.androidlifelang.corepresentation.model.LanguageUi.VI
import com.androidlifelang.corepresentation.model.ThemeUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSettingFragment : PreferenceFragmentCompat() {

    private var kTheme: Preference? = null
    private var kLanguage: Preference? = null
    private var kVersion: Preference? = null

    private val viewModel: DetailSettingViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        bindView()
        initView()
        observer()
    }

    private fun bindView() {
        kTheme = findPreference("k_theme")
        kVersion = findPreference("k_version")
        kLanguage = findPreference("k_language")
    }

    private fun initView() {
        kVersion?.summary = BuildConfig.VERSION_CODE.toString()

        kTheme?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.setting_theme)
            true
        }

        kLanguage?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.setting_language)
            true
        }
    }

    private fun observer() {
        viewModel.themeObservable.observe(this, ::setTheme)
        viewModel.currentTheme.observe(this, EventObserver(::setTheme))
        viewModel.languageObservable.observe(this, ::setLanguage)
        viewModel.currentLanguage.observe(this, EventObserver(::setLanguage))
    }

    private fun setTheme(theme: ThemeUi?) {
        kTheme?.setSummary(
            when (theme) {
                ThemeUi.LIGHT -> R.string.setting_theme_light
                ThemeUi.DARK -> R.string.setting_theme_dark
                ThemeUi.SYSTEM -> R.string.setting_theme_system
                ThemeUi.BATTERY_SAVER -> R.string.setting_theme_battery_save
                else -> R.string.setting_theme_system
            }
        )
    }

    private fun setLanguage(language: LanguageUi) {
        kLanguage?.setSummary(
            when (language) {
                VI -> R.string.setting_language_vi
                EN -> R.string.setting_language_en
            }
        )
    }

    override fun onDestroyView() {
        kTheme = null
        kLanguage = null
        kVersion = null
        super.onDestroyView()
    }

    companion object {
        fun create(): DetailSettingFragment = DetailSettingFragment()
    }
}
