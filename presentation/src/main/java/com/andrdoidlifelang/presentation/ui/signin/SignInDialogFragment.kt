package com.andrdoidlifelang.presentation.ui.signin

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.DialogSignInBinding
import com.andrdoidlifelang.presentation.ui.base.BaseDialogFragment
import com.andrdoidlifelang.presentation.util.Constant
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_SIGN_IN_DIALOG_NAME

class SignInDialogFragment : BaseDialogFragment<DialogSignInBinding, SignInViewModel>() {

    override val viewModel: SignInViewModel by viewModels()

    override val layoutId: Int = R.layout.dialog_sign_in

    override val screenName: String = ANALYTICS_SIGN_IN_DIALOG_NAME
}
