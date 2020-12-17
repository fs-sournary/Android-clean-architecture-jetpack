package com.andrdoidlifelang.data.model

/**
 * Data is set by the server and used for a reservation request's result in the firestore.
 */
data class ReservationRequestResultEntity(
    // The id of a [ReservationRequestEntity].
    val requestId: String,
    val timestamp: Long = -1,
    val status: ReservationRequestResultStatus
)

enum class ReservationRequestResultStatus {

    /** The reservation was granted */
    RESERVE_SUCCEEDED,

    /** The reservation was granted but the user was place into the wait list */
    RESERVE_WAIT_LIST,

    /** The reservation was denied because it was too close to the start of the event */
    RESERVE_DENIED_CUT_OFF,

    /** The reservation was denied because it overlapped with another reservation or wait list */
    RESERVE_DENIED_CLASH,

    /** The reservation was denied for unknown reasons */
    RESERVE_DENIED_UNKNOWN,

    /** The reservation was successfully cancelled */
    CANCEL_SUCCEEDED,

    /** The reservation was granted by a Swap request */
    SWAP_SUCCEEDED,

    /** The reservation was denied because it was too close to the start of the event */
    SWAP_DENIED_CUT_OFF,

    /** The reservation was denied because it overlapped with another reservation or wait list */
    SWAP_DENIED_CLASH,

    /** The swap was denied for unknown reasons */
    SWAP_DENIED_UNKNOWN;

    companion object {

        fun getIfPresent(value: String): ReservationRequestResultStatus? = try {
            valueOf(value)
        } catch (e: IllegalStateException) {
            null
        }
    }
}
