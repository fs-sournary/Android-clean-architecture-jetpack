package com.andrdoidlifelang.presentation.ui.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.andrdoidlifelang.presentation.BR

/**
 * The [RecyclerView.ViewHolder]'s base class that uses data binding to bind data into item View.
 *
 * @property T the data type that is bind into item View.
 * @property ViewBinding the binding type that is generated from item View,
 *
 * @param binding the binding that is generated from item View.
 * @param lifecycleOwner the lifecycle owner of binding such as ViewLifecycleOwner of a Fragment.
 */
open class BaseListViewHolder<T, ViewBinding : ViewDataBinding>(
    private val binding: ViewBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: T) {
        binding.apply {
            bind(binding, item, bindingAdapterPosition)
            setVariable(BR.item, item)
            lifecycleOwner = this@BaseListViewHolder.lifecycleOwner
            executePendingBindings()
        }
    }

    open fun bind(binding: ViewBinding, item: T, position: Int) {}
}
