package com.andrdoidlifelang.presentation.util

import androidx.lifecycle.Observer
import com.andrdoidlifelang.domain.result.Event

/**
 * An [Observer] for [Event]s that checks if the content has already handled.
 *
 * @param onEventUnHandledContent the action is invoked when the content hasn't handled.
 */
class EventObserver<T>(private val onEventUnHandledContent: (T) -> Unit) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { onEventUnHandledContent(it) }
    }
}
