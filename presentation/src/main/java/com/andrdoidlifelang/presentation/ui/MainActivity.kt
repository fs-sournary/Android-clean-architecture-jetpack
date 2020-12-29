package com.andrdoidlifelang.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.ActivityMainBinding
import com.andrdoidlifelang.presentation.databinding.NavDrawerHeaderBinding
import com.andrdoidlifelang.presentation.ui.base.NavigationHostListener
import com.andrdoidlifelang.presentation.ui.login.LoginActivity
import com.andrdoidlifelang.presentation.util.Constant
import com.andrdoidlifelang.presentation.util.EventObserver
import com.andrdoidlifelang.presentation.widget.MovieHashTagItemDecoration
import com.androidlifelang.corepresentation.ext.createNavigationDrawerItemBackground
import com.androidlifelang.corepresentation.ext.getSystemWindowInsetEdge
import com.androidlifelang.corepresentation.ext.requestInsetsWhenAttached
import com.androidlifelang.corepresentation.ext.shouldCloseFromBackPress
import com.androidlifelang.corepresentation.ext.updateTheme
import com.androidlifelang.corepresentation.ui.CoreActivity
import com.androidlifelang.corepresentation.utils.EdgeToEdge
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : CoreActivity<ActivityMainBinding, MainViewModel>(), NavigationHostListener, NavigationView.OnNavigationItemSelectedListener {

    companion object {

        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var currentNavId = Constant.NAV_ID_NONE

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override val viewModel: MainViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateTheme(viewModel.currentTheme)
        observer()
        setupWindow()
        setupNavigation()
    }

    private fun observer() {
        with(viewModel) {
            theme.observe(this@MainActivity, EventObserver(::updateTheme))

            logoutEvent.observe(this@MainActivity) {
                it.getContentIfNotHandled()?.let {

                    finish()
                    startActivity(LoginActivity.getIntent(this@MainActivity))
                }
            }
        }
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
            val lockMode = when (Constant.NAV_HOST_DESTINATIONS.contains(destination.id)) {
                true -> DrawerLayout.LOCK_MODE_UNLOCKED
                false -> DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            }
            binding.drawer.setDrawerLockMode(lockMode)
        }
        // ------------ Drawer ----------//
        val navHeaderBinding = NavDrawerHeaderBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
        val navHeader = navHeaderBinding.root
        val navHeaderPaddingTop = navHeader.paddingTop
        binding.navigation.apply {
            val navigationMenu = findViewById<RecyclerView>(R.id.design_navigation_view).apply {
                addItemDecoration(MovieHashTagItemDecoration(context))
            }
            navHeader.setOnApplyWindowInsetsListener { _, insets ->
                navHeader.updatePadding(
                    top = navHeaderPaddingTop + insets.getSystemWindowInsetEdge().top
                )
                navigationMenu?.updatePadding(bottom = insets.getSystemWindowInsetEdge().bottom)
                insets
            }
            navHeader.requestInsetsWhenAttached()
            addHeaderView(navHeader)
            itemBackground = context.createNavigationDrawerItemBackground()
            setupWithNavController(navController)
            setNavigationItemSelectedListener(this@MainActivity)
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        val configuration = AppBarConfiguration(Constant.NAV_HOST_DESTINATIONS, binding.drawer)
        toolbar.setupWithNavController(navController, configuration)
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(binding.navigation) && binding.drawer.shouldCloseFromBackPress()) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout_dest -> {
            viewModel.logout()
            true
        }
        else -> {
            NavigationUI.onNavDestinationSelected(item, navController)
            drawer?.closeDrawer(GravityCompat.START)
            true
        }
    }
}
