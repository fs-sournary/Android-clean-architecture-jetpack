package com.androidlifelang.corepresentation.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class CoreActivity<ViewBinding : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    lateinit var binding: ViewBinding

    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        updateLanguage()
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    open fun updateLanguage() {}
}
