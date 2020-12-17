package com.andrdoidlifelang.domain.result

/**
 * The wrapper is used for event properties that is exposed via LiveData such as show toast,
 * show dialog, navigation...
 */
open class Event<out T>(private val content: T) {

    private var isHandled = false

    /**
     * This method returns the content and prevent it is used again.
     */
    fun getContentIfNotHandled(): T? = if (isHandled) {
        null
    } else {
        isHandled = true
        content
    }

    /**
     * This method returns the content, even it has already used.
     */
    fun peekContent(): T = content
}
