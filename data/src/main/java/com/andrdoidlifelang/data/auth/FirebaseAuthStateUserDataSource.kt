package com.andrdoidlifelang.data.auth

import com.andrdoidlifelang.data.fcm.FcmTokenUpdater
import com.andrdoidlifelang.data.mapper.map
import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.repository.SignInRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map

/**
 * A [SignInRepository] that listens to the changes of [FirebaseAuth]. When a [FirebaseUser]
 * is available, this class will:
 * 1, Posts a [AuthBasicUserInfo].
 * 2, Fetches the user id token.
 * 3, Store fcm installation token into firestore.
 */
class FirebaseAuthStateUserDataSource(
    private val firebaseAuth: FirebaseAuth,
    private val fcmTokenUpdater: FcmTokenUpdater
) : AuthStateUserDataSource {

    override fun getBasicUserInfo(): Flow<UseCaseResult<AuthBasicUserInfo>> {
        return channelFlow<FirebaseAuth> {
            val authStateChangeListener: (FirebaseAuth) -> Unit = { channel.offer(it) }
            firebaseAuth.addAuthStateListener(authStateChangeListener)
            // Remove listener when the flow is cancelled or closed.
            awaitClose { firebaseAuth.removeAuthStateListener(authStateChangeListener) }
        }.map { processAuthState(it) }
    }

    private suspend fun processAuthState(auth: FirebaseAuth): UseCaseResult<AuthBasicUserInfo> {
        when (val currentUser = auth.currentUser) {
            null -> {
                // Todo: Handle when user is null
            }
            else -> {
                // Upload fcm installation token into firestore.
                fcmTokenUpdater.updateFcmToken(currentUser.uid)
            }
        }
        return UseCaseResult.Success(auth.map())
    }
}
