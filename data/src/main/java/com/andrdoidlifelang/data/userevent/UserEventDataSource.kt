package com.andrdoidlifelang.data.userevent

import com.andrdoidlifelang.data.model.ReservationRequestAction
import com.andrdoidlifelang.data.model.StarUpdateStatus
import com.andrdoidlifelang.data.model.UserEventEntity
import com.andrdoidlifelang.data.model.UserEventResultEntity
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface UserEventDataSource {

    /** ========== Firestore get ========== */
    fun getUserEvents(userId: String): Flow<UserEventResultEntity>

    /** ========== Firestore set ========== */
    suspend fun starEvent(
        userId: String,
        userEvent: UserEventEntity
    ): UseCaseResult<StarUpdateStatus>

    suspend fun recordFeedback(userId: String, userEvent: UserEventEntity): UseCaseResult<Unit>

    suspend fun requestReservation(
        userId: String,
        sessionId: String,
        action: ReservationRequestAction
    ): UseCaseResult<ReservationRequestAction>

    suspend fun swapReservation(
        userId: String,
        fromSessionId: String,
        toSessionId: String
    ): UseCaseResult<Unit>
}
