package com.andrdoidlifelang.presentation.analytics

import android.app.Activity

/**
 * Analytics API interface.
 */
interface AnalyticsHelper {

    /**
     * Record screen view.
     */
    fun sendScreenView(screenName: String, activity: Activity)

    /**
     * Log a UI event such as click a button.
     */
    fun logUiEvent(itemId: String, action: String)

    /**
     * Set the user signed in property.
     */
    fun setUserSignedIn(isSignedIn: Boolean)

    /**
     * Set the user registered property.
     */
    fun setUserRegistered(registered: Boolean)
}
