package com.andrdoidlifelang.presentation.ui.signin

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.DialogSignInBinding
import com.androidlifelang.corepresentation.ui.BaseDialogFragment

class SignInDialogFragment : BaseDialogFragment<DialogSignInBinding, SignInViewModel>() {

    override val viewModel: SignInViewModel by viewModels()

    override val layoutId: Int = R.layout.dialog_sign_in
}
