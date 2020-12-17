package com.andrdoidlifelang.data.userevent

import com.andrdoidlifelang.data.di.IoDispatcher
import com.andrdoidlifelang.data.ext.suspendAndWait
import com.andrdoidlifelang.data.mapper.getUserEvent
import com.andrdoidlifelang.data.model.ReservationRequestAction
import com.andrdoidlifelang.data.model.ReservationRequestAction.CancelAction
import com.andrdoidlifelang.data.model.ReservationRequestAction.RequestAction
import com.andrdoidlifelang.data.model.ReservationRequestAction.SwapAction
import com.andrdoidlifelang.data.model.StarUpdateStatus
import com.andrdoidlifelang.data.model.UserEventEntity
import com.andrdoidlifelang.data.model.UserEventResultEntity
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_EVENT_COLLECTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_IS_STARRED_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_QUEUE_COLLECTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_ACTION_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REQUEST_TIMESTAMP_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVATION_REQUEST_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVE_CANCEL_ACTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVE_REQUEST_ACTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_RESERVE_SWAP_ACTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_REVIEWED_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_SESSION_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_SWAP_CANCEL_RESERVE_SESSION_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_SWAP_REQUEST_RESERVE_SESSION_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_USER_COLLECTION
import com.andrdoidlifelang.domain.result.UseCaseResult
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.*

class FirestoreUserEventDataSource(
    private val firestore: FirebaseFirestore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserEventDataSource {

    /** ========== Firestore get ========== */
    override fun getUserEvents(userId: String): Flow<UserEventResultEntity> {
        if (userId.isEmpty()) {
            return flow {
                emit(UserEventResultEntity())
            }
        }
        return channelFlow {
            val eventCollection = firestore.collection(FIRESTORE_USER_COLLECTION)
                .document(userId)
                .collection(FIRESTORE_EVENT_COLLECTION)
            val currentValue: UserEventResultEntity? = null
            val subscription = eventCollection.addSnapshotListener { snapShot, _ ->
                if (snapShot == null) {
                    return@addSnapshotListener
                }
                val userEventResultEntity = UserEventResultEntity(
                    userEvents = snapShot.documents.map { it.getUserEvent() },
                    userEventMessage = null
                )
                offer(userEventResultEntity)
            }
            awaitClose { subscription.remove() }
        }.flowOn(ioDispatcher)
    }

    /** ========== Firestore set ========== */
    override suspend fun starEvent(
        userId: String,
        userEvent: UserEventEntity
    ): UseCaseResult<StarUpdateStatus> = withContext(ioDispatcher) {
        val data = mapOf(
            FIRESTORE_ID_KEY to userEvent.id,
            FIRESTORE_IS_STARRED_KEY to userEvent.isStarred
        )
        try {
            firestore.collection(FIRESTORE_USER_COLLECTION)
                .document(userId)
                .collection(FIRESTORE_EVENT_COLLECTION)
                .document(userEvent.id)
                .set(data, SetOptions.merge())
                .suspendAndWait()
            val result = when (userEvent.isStarred) {
                true -> StarUpdateStatus.STARRED
                else -> StarUpdateStatus.UNSTARRED
            }
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun recordFeedback(
        userId: String,
        userEvent: UserEventEntity
    ): UseCaseResult<Unit> = withContext(ioDispatcher) {
        val data = mapOf(
            FIRESTORE_USER_COLLECTION to userId,
            FIRESTORE_REVIEWED_KEY to userEvent.reviewed
        )
        try {
            firestore.collection(FIRESTORE_USER_COLLECTION)
                .document(userId)
                .collection(FIRESTORE_EVENT_COLLECTION)
                .document(userEvent.id)
                .set(data, SetOptions.merge())
                .suspendAndWait()
            UseCaseResult.Success(Unit)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    override suspend fun requestReservation(
        userId: String,
        sessionId: String,
        action: ReservationRequestAction
    ): UseCaseResult<ReservationRequestAction> = withContext(ioDispatcher) {
        val batch = firestore.batch()
        val requestId = UUID.randomUUID().toString()
        val requestAction = getReservationRequestEventAction(action)
        // Write 1: Mark this as reserved. This is for clients to track.
        val userSessionDocument = firestore.collection(FIRESTORE_USER_COLLECTION)
            .document(userId)
            .collection(FIRESTORE_EVENT_COLLECTION)
            .document(sessionId)
        val reservationRequest = mapOf(
            FIRESTORE_REQUEST_ACTION_KEY to requestAction,
            FIRESTORE_REQUEST_ID_KEY to requestId,
            FIRESTORE_REQUEST_TIMESTAMP_KEY to FieldValue.serverTimestamp()
        )
        val userSession = mapOf(
            FIRESTORE_ID_KEY to sessionId,
            FIRESTORE_RESERVATION_REQUEST_KEY to reservationRequest
        )
        batch.set(userSessionDocument, userSession, SetOptions.merge())
        // Write 2: Send a request to the server.
        val queueDocument = firestore.collection(FIRESTORE_QUEUE_COLLECTION).document(userId)
        val queueReservationRequest = mapOf(
            FIRESTORE_REQUEST_ACTION_KEY to requestAction,
            FIRESTORE_SESSION_ID_KEY to sessionId,
            FIRESTORE_REQUEST_ID_KEY to requestId
        )
        batch.set(queueDocument, queueReservationRequest, SetOptions.merge())
        try {
            batch.commit().suspendAndWait()
            UseCaseResult.Success(action)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }

    private fun getReservationRequestEventAction(
        action: ReservationRequestAction
    ): String? = when (action) {
        is RequestAction -> FIRESTORE_RESERVE_REQUEST_ACTION
        is CancelAction -> FIRESTORE_RESERVE_CANCEL_ACTION
        is SwapAction -> null
    }

    override suspend fun swapReservation(
        userId: String,
        fromSessionId: String,
        toSessionId: String
    ): UseCaseResult<Unit> = withContext(ioDispatcher) {
        val batch = firestore.batch()
        val requestId = UUID.randomUUID().toString()
        val timestamp = FieldValue.serverTimestamp()
        // Write 1: Mark [toSessionId] as reserved. This is for client to track.
        val toSessionDocument = firestore.collection(FIRESTORE_USER_COLLECTION)
            .document(userId)
            .collection(FIRESTORE_EVENT_COLLECTION)
            .document(toSessionId)
        val toSessionReservationRequest = mapOf(
            FIRESTORE_REQUEST_ACTION_KEY to FIRESTORE_RESERVE_REQUEST_ACTION,
            FIRESTORE_REQUEST_ID_KEY to requestId,
            FIRESTORE_REQUEST_TIMESTAMP_KEY to timestamp
        )
        batch.set(toSessionDocument, toSessionReservationRequest, SetOptions.merge())
        // Write 2: Mark [fromSessionId} as cancelled. This is for client to track.
        val fromSessionDocument = firestore.collection(FIRESTORE_USER_COLLECTION)
            .document(userId)
            .collection(FIRESTORE_EVENT_COLLECTION)
            .document(fromSessionId)
        val fromSessionReservationRequest = mapOf(
            FIRESTORE_REQUEST_ACTION_KEY to FIRESTORE_RESERVE_CANCEL_ACTION,
            FIRESTORE_REQUEST_ID_KEY to requestId,
            FIRESTORE_REQUEST_TIMESTAMP_KEY to timestamp
        )
        batch.set(fromSessionDocument, fromSessionReservationRequest, SetOptions.merge())
        // Write 3: Notify the reservation changed from [fromSessionId] to [toSessionId]
        val queueDocument = firestore.collection(FIRESTORE_QUEUE_COLLECTION).document(userId)
        val queueReservationRequest = mapOf(
            FIRESTORE_REQUEST_ACTION_KEY to FIRESTORE_RESERVE_SWAP_ACTION,
            FIRESTORE_SWAP_REQUEST_RESERVE_SESSION_ID_KEY to toSessionId,
            FIRESTORE_SWAP_CANCEL_RESERVE_SESSION_ID_KEY to fromSessionId,
            FIRESTORE_REQUEST_ID_KEY to requestId
        )
        batch.set(queueDocument, queueReservationRequest, SetOptions.merge())
        try {
            batch.commit().suspendAndWait()
            UseCaseResult.Success(Unit)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}
