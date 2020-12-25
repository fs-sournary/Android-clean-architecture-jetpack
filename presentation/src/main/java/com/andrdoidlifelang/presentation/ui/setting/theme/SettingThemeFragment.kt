package com.andrdoidlifelang.presentation.ui.setting.theme

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.model.ThemeHolderUi
import com.andrdoidlifelang.presentation.util.Constant
import com.andrdoidlifelang.presentation.util.analytics.AnalyticsHelper
import com.androidlifelang.corepresentation.ext.autoClear
import com.androidlifelang.corepresentation.model.ThemeUi
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingThemeFragment : AppCompatDialogFragment() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    private val viewModel: SettingThemeViewModel by viewModels()
    private val screenName = Constant.ANALYTICS_SETTING_THEME_SCREEN_NAME
    private var listAdapter by autoClear<ArrayAdapter<ThemeHolderUi>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.availableThemes.observe(this) {
            listAdapter.clear()
            listAdapter.addAll(it.map { result -> ThemeHolderUi(result, getTitleForTheme(result)) })

            updateSelectedItem(viewModel.theme.value)
        }

        viewModel.theme.observe(this, ::updateSelectedItem)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_single_choice)

        return MaterialAlertDialogBuilder(context ?: requireContext())
            .setTitle(R.string.setting_theme_title)
            .setSingleChoiceItems(listAdapter, 0) { dialog, position ->
                listAdapter.getItem(position)?.theme?.let {
                    viewModel.setTheme(it)
                }
                dialog.dismiss()
            }
            .create()
    }

    private fun updateSelectedItem(selected: ThemeUi?) {
        val selectedPosition = (0 until listAdapter.count).indexOfFirst { index ->
            listAdapter.getItem(index)?.theme == selected
        }
        (dialog as AlertDialog).listView.setItemChecked(selectedPosition, true)
    }

    private fun getTitleForTheme(theme: ThemeUi): String = getString(
        when (theme) {
            ThemeUi.DARK -> R.string.setting_theme_dark
            ThemeUi.LIGHT -> R.string.setting_theme_light
            ThemeUi.BATTERY_SAVER -> R.string.setting_theme_battery_save
            ThemeUi.SYSTEM -> R.string.setting_theme_system
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsHelper.sendScreenView(screenName, requireActivity())
    }
}
