package com.andrdoidlifelang.data.util

object Constant {

    // Api
    const val API_PROCESS_TIMEOUT = 10L
    const val API_KEY_PARAMS = "api_key"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    // Preference
    const val PREF_NAME = "androidlifelang_pref"
    const val DEF_PREF_STRING = ""
    const val ACCESS_TOKEN_KEY = "access_token_key"
    const val PREF_DARK_MODE_ENABLE = "pref_dark_mode"

    // Database
    const val MOVIE_DB_NAME = "movie.db"

    // Analytics
    const val ANALYTICS_UI_EVENT_CONTENT_TYPE = "ui event"
    const val ANALYTICS_UI_ACTION_PARAM = "ui_action"
    const val ANALYTICS_USER_SIGNED_IN_PROPERTY = "user_signed_in"
    const val ANALYTICS_USER_REGISTERED_PROPERTY = "user_registered"

    //====== Firestore =====//
    const val FIRESTORE_USER_COLLECTION = "user"
    const val FIRESTORE_FCM_TOKEN_COLLECTION = "fcmToken"
    const val FIRESTORE_EVENT_COLLECTION = "event"
    const val FIRESTORE_QUEUE_COLLECTION = "queue"
    const val FIRESTORE_LAST_VISIT_KEY = "lastVisit"
    const val FIRESTORE_TOKEN_ID_KEY = "tokenId"
    const val FIRESTORE_ID_KEY = "id"
    const val FIRESTORE_IS_STARRED_KEY = "isStarred"
    const val FIRESTORE_REVIEWED_KEY = "reviewed"
    const val FIRESTORE_REQUEST_ACTION_KEY = "action"
    const val FIRESTORE_REQUEST_ID_KEY = "requestId"
    const val FIRESTORE_SESSION_ID_KEY = "sessionId"
    const val FIRESTORE_REQUEST_TIMESTAMP_KEY = "timestamp"
    const val FIRESTORE_RESERVATION_REQUEST_KEY = "reservationRequest"
    const val FIRESTORE_SWAP_REQUEST_RESERVE_SESSION_ID_KEY = "reserveSessionId"
    const val FIRESTORE_SWAP_CANCEL_RESERVE_SESSION_ID_KEY = "cancelSessionId"
    // Server key
    const val FIRESTORE_RESERVE_STATUS_KEY = "reservationStatus"
    const val FIRESTORE_RESERVE_RESULT_KEY = "reservationResult"
    const val FIRESTORE_RESERVE_REQUEST_ACTION = "RESERVE_REQUESTED"
    const val FIRESTORE_RESERVE_CANCEL_ACTION = "CANCEL_REQUESTED"
    const val FIRESTORE_RESERVE_SWAP_ACTION = "SWAP_REQUEST"
    const val FIRESTORE_TOKEN_MAX_LENGTH = 25

    // Fcm
    const val FCM_MOVIE_UPDATE_TOPIC = "movie_updates"
    const val FCM_CHARACTER_TOPIC = "character"
}
