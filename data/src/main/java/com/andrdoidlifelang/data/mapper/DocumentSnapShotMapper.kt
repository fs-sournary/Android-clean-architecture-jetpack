package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.data.model.ReservationRequestEntity
import com.andrdoidlifelang.data.model.ReservationRequestEntityAction
import com.andrdoidlifelang.data.model.ReservationRequestResultEntity
import com.andrdoidlifelang.data.model.ReservationRequestResultStatus
import com.andrdoidlifelang.data.model.ReservationStatus
import com.andrdoidlifelang.data.model.UserEventEntity
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_IS_STARRED_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_ACTION_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_TIMESTAMP_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVATION_REQUEST_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVE_RESULT_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVE_STATUS_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REVIEWED_KEY
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.getUserEvent(): UserEventEntity = UserEventEntity(
    id = id,
    isStarred = this[FIRESTORE_IS_STARRED_KEY] as? Boolean,
    reviewed = this[FIRESTORE_REVIEWED_KEY] as? Boolean,
    reservationRequest = getReservationRequest(),
    reservationRequestResult = getReservationRequestResult(),
    reservationStatus = getReservationStatus()
)

private fun DocumentSnapshot.getReservationRequest(): ReservationRequestEntity? {
    val reservationRequest = (this[FIRESTORE_RESERVATION_REQUEST_KEY] as? Map<*, *>) ?: return null
    val action = (reservationRequest[FIRESTORE_REQUEST_ACTION_KEY] as? String)?.let {
        ReservationRequestEntityAction.getIfPresent(it)
    } ?: return null
    val requestId = (reservationRequest[FIRESTORE_REQUEST_ID_KEY] as? String) ?: return null
    return ReservationRequestEntity(requestId, action)
}

private fun DocumentSnapshot.getReservationRequestResult(): ReservationRequestResultEntity? {
    val reservationResult = (this[FIRESTORE_RESERVE_RESULT_KEY] as? Map<*, *>) ?: return null
    val requestId = (reservationResult[FIRESTORE_REQUEST_ID_KEY] as? String) ?: return null
    val timestamp = (reservationResult[FIRESTORE_REQUEST_TIMESTAMP_KEY] as? Long) ?: -1
    val status = (reservationResult[FIRESTORE_RESERVE_STATUS_KEY] as? String)?.let {
        ReservationRequestResultStatus.getIfPresent(it)
    } ?: return null
    return ReservationRequestResultEntity(requestId, timestamp, status)
}

private fun DocumentSnapshot.getReservationStatus(): ReservationStatus? =
    (this[FIRESTORE_RESERVE_STATUS_KEY] as? String)?.let { ReservationStatus.getIfPresent(it) }
