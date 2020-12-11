package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.ActivityMainBinding
import com.andrdoidlifelang.android_clean_architecture_jetpack.util.Constant
import com.androidlifelang.base.ext.getSystemWindowInsetEdge
import com.androidlifelang.base.ext.updateTheme
import com.androidlifelang.base.model.Theme
import com.androidlifelang.base.ui.BaseActivity
import com.androidlifelang.base.utils.EdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_main

    private var currentNavId = Constant.NAV_ID_NONE

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateTheme(Theme.LIGHT)
        setupWindow()
        setupNavigation()
    }

    private fun setupWindow() {
        EdgeToEdge.setupRoot(container, window)
        container.setOnApplyWindowInsetsListener { _, windowInsets ->
            // Let the container draw it's navigation bar divider.
            container.onApplyWindowInsets(windowInsets)
            // Consume horizontal insets and pad all content in.
            val systemWindowInsetEdge = windowInsets.getSystemWindowInsetEdge()
            container.updatePadding(
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
            drawer.setDrawerLockMode(lockMode)
        }
        // Drawer
        navigation.setupWithNavController(navController)
    }
}
