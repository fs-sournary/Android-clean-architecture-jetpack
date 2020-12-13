package com.androidlifelang.corepresentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.observe
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wrapper for lazy properties that is clean up when their own fragment's view is destroyed.
 * Accessing properties when their own fragment's view is destroyed will be thrown an exception.
 *
 * @param fragment the own fragment.
 */
class AutoClearedValue<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {

    private var currentValue: T? = null

    init {
        fragment.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) {
                    it.lifecycle.addObserver(object : LifecycleObserver {
                        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                        fun onDestroy() {
                            currentValue = null
                        }
                    })
                }
            }
        })
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        currentValue = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        currentValue ?: throw IllegalArgumentException("Auto-cleared-value is not available")
}
