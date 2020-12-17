package com.andrdoidlifelang.data.model

/**
 * The reservation status of a event is set by the server.
 */
enum class ReservationStatus {

    // The reservation was granted.
    RESERVED,

    // The reservation was granted but the user was placed on a wait list.
    WAIT_LIST,

    // The reservation request was denied because it is too close to the start of the event.
    NONE;

    companion object {

        fun getIfPresent(value: String): ReservationStatus? = try {
            valueOf(value)
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
