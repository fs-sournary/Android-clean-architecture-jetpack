package com.andrdoidlifelang.data.analytics

import android.app.Activity
import com.andrdoidlifelang.data.util.Constant
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class FirebaseAnalyticsHelper : AnalyticsHelper {

    private val firebaseAnalytics = Firebase.analytics

    override fun sendScreenView(screenName: String, activity: Activity) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, activity.localClassName)
        }
    }

    override fun logUiEvent(itemId: String, action: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, itemId)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, Constant.ANALYTICS_UI_EVENT_CONTENT_TYPE)
            param(Constant.ANALYTICS_UI_ACTION_PARAM, action)
        }
    }

    override fun setUserSignedIn(isSignedIn: Boolean) {
        firebaseAnalytics.setUserProperty(
            Constant.ANALYTICS_USER_SIGNED_IN_PROPERTY,
            isSignedIn.toString()
        )
    }

    override fun setUserRegistered(isRegistered: Boolean) {
        firebaseAnalytics.setUserProperty(
            Constant.ANALYTICS_USER_REGISTERED_PROPERTY,
            isRegistered.toString()
        )
    }
}
