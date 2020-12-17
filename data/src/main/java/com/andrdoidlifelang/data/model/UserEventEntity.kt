package com.andrdoidlifelang.data.model

/**
 * Data for every user event stored in the firestore.
 */
data class UserEventEntity(
    // The unique id of a user event.
    val id: String,
    // Check whenever a user event has starred.
    val isStarred: Boolean? = null,
    // Check whenever user provided feedback to user.
    val reviewed: Boolean? = null,
    val reservationRequest: ReservationRequestEntity? = null,
    val reservationRequestResult: ReservationRequestResultEntity? = null,
    val reservationStatus: ReservationStatus? = null
)
