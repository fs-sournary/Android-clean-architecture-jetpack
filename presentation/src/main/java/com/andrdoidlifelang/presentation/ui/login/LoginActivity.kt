package com.andrdoidlifelang.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.ActivityLoginBinding
import com.androidlifelang.corepresentation.ext.updateTheme
import com.androidlifelang.corepresentation.ui.CoreActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : CoreActivity<ActivityLoginBinding, LoginActivityViewModel>() {
    companion object {

        fun getIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override val viewModel: LoginActivityViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateTheme(viewModel.currentTheme)
    }
}
