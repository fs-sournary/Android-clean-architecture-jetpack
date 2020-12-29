package com.andrdoidlifelang.data.model

data class ErrorResponse(
    val type: String? = null,
    val message: String? = null
) {

    // Define some type between server and client side agreed with each other
    companion object {
        const val TYPE_APP_FORCE_UPDATE = "ForceUpdateAppException"
        const val TYPE_SERVER_MAINTENANCE = "ServerMaintenanceException"
    }
}
