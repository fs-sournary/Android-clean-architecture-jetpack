package com.andrdoidlifelang.presentation.util

import com.andrdoidlifelang.presentation.R

object Constant {

    // Common
    const val CHROME_PACKAGE = "com.android.chrome"

    // Navigation
    const val NAV_ID_NONE = -1
    val NAV_HOST_DESTINATIONS = setOf(
        R.id.home_dest,
        R.id.schedule_dest,
        R.id.agenda_dest,
        R.id.map_dest,
        R.id.info_dest,
        R.id.setting_dest
    )

    // Analytics
    const val ANALYTICS_HOME_SCREEN_NAME = "home"
    const val ANALYTICS_SCHEDULE_SCREEN_NAME = "schedule"
    const val ANALYTICS_AGENDA_SCREEN_NAME = "agenda"
    const val ANALYTICS_MAP_SCREEN_NAME = "map"
    const val ANALYTICS_INFO_SCREEN_NAME = "info"
    const val ANALYTICS_SETTING_SCREEN_NAME = "setting"
    const val ANALYTICS_SIGN_IN_DIALOG_NAME = "sign_in"
    const val ANALYTICS_SETTING_THEME_SCREEN_NAME = "setting_theme"
}
