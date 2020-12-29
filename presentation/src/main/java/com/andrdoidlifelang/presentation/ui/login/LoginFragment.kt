package com.andrdoidlifelang.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.andrdoidlifelang.presentation.BuildConfig
import com.andrdoidlifelang.presentation.databinding.FragmentLoginBinding
import com.andrdoidlifelang.presentation.extention.hideKeyBoard
import com.andrdoidlifelang.presentation.ui.MainActivity
import com.andrdoidlifelang.presentation.ui.base.BaseFragment
import com.andrdoidlifelang.presentation.ui.base.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override val layoutId: Int = com.andrdoidlifelang.presentation.R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        observe()
    }

    private fun observe() {
        with(viewModel) {
            loginResult.observe(viewLifecycleOwner) { result ->
                this@LoginFragment.hideKeyBoard()

                result.getContentIfNotHandled()?.let {
                    when (it) {
                        is Result.Loading -> {
                            showDialogLoading()
                        }
                        is Result.Success -> {
                            hideDialogLoading()

                            activity?.apply {
                                finish()
                                startActivity(MainActivity.getIntent(this))
                            }
                        }
                        is Result.Error -> {
                            hideDialogLoading()
                        }
                    }
                }
            }
        }
    }

    private fun setupListener() {
        buttonLogin.setOnClickListener {
            viewModel.login()
        }

        if (BuildConfig.DEBUG) {
            textUsername.setText("admin")
            textPassword.setText("1")
        }
    }

    override val screenName: String = "LoginFragment"
}
