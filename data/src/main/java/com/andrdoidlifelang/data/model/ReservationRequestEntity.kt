package com.andrdoidlifelang.data.model

/**
 * Entity that represents the client's latest reservation or cancelled request. This is used to
 * figure out whether a reservation request is pending or completed.
 */
data class ReservationRequestEntity(
    // An Id that is set by the client and added to the reservation result on completion.
    val requestId: String,
    // The action of the reservation request (REQUEST/CANCEL).
    val action: ReservationRequestEntityAction
)

enum class ReservationRequestEntityAction {

    // The reservation request was granted.
    RESERVED,

    // The reservation was granted but the user was placed on the wait list.
    CANCELLED;

    companion object {

        fun getIfPresent(value: String): ReservationRequestEntityAction? = try {
            valueOf(value)
        } catch (e: IllegalStateException) {
            null
        }
    }
}
