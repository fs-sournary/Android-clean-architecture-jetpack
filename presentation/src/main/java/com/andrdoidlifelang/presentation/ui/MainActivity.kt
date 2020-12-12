package com.andrdoidlifelang.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.ActivityMainBinding
import com.andrdoidlifelang.presentation.util.Constant
import com.androidlifelang.base.ext.getSystemWindowInsetEdge
import com.androidlifelang.base.ext.updateTheme
import com.androidlifelang.base.model.Theme
import com.androidlifelang.base.ui.BaseActivity
import com.androidlifelang.base.utils.EdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var currentNavId = Constant.NAV_ID_NONE

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override val viewModel: MainViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        updateTheme(Theme.LIGHT)
        super.onCreate(savedInstanceState)
        setupWindow()
        setupNavigation()
    }

    private fun setupWindow() {
        EdgeToEdge.setupRoot(binding.container, window)
        binding.container.setOnApplyWindowInsetsListener { _, windowInsets ->
            // Let the container draw it's navigation bar divider.
            binding.container.onApplyWindowInsets(windowInsets)
            // Consume horizontal insets and pad all content in.
            val systemWindowInsetEdge = windowInsets.getSystemWindowInsetEdge()
            binding.container.updatePadding(
                left = systemWindowInsetEdge.left,
                right = systemWindowInsetEdge.right
            )
            windowInsets
        }
    }

    private fun setupNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
            val lockMode = when (Constant.NAV_DRAWER_DESTINATIONS.contains(destination.id)) {
                true -> DrawerLayout.LOCK_MODE_UNLOCKED
                false -> DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            }
            binding.drawer.setDrawerLockMode(lockMode)
        }
        // Drawer
        binding.navigation.setupWithNavController(navController)
    }
}
