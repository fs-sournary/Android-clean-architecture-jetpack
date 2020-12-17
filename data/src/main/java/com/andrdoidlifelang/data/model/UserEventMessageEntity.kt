package com.andrdoidlifelang.data.model

data class UserEventMessageEntity(
    val sessionId: String? = null,
    val changeRequestId: String? = null,
    val type: UserEventMessageChangeType? = null
)

/**
 * Enum of messages
 */
enum class UserEventMessageChangeType {
    CHANGES_IN_RESERVATIONS,
    RESERVATION_REPLACED,
    CHANGES_IN_WAIT_LIST,
    RESERVATION_CANCELED,
    WAIT_LIST_CANCELED
}
