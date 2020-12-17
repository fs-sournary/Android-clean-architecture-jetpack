package com.andrdoidlifelang.data.fcm

import com.andrdoidlifelang.data.di.ApplicationScope
import com.andrdoidlifelang.data.di.MainDispatcher
import com.andrdoidlifelang.data.ext.suspendAndWait
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_FCM_TOKEN_COLLECTION
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_LAST_VISIT_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_TOKEN_ID_KEY
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_TOKEN_MAX_LENGTH
import com.andrdoidlifelang.data.util.Constant.FIRESTORE_USER_COLLECTION
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class saves FCM installation token into Firestore.
 */
@Singleton
class FcmTokenUpdater @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseMessaging: FirebaseMessaging,
    @ApplicationScope private val applicationScope: CoroutineScope,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) {

    suspend fun updateFcmToken(uid: String) {
        try {
            // fetch fcm token (force refresh)
            val tokenTask = firebaseMessaging.token
            val token = tokenTask.suspendAndWait()
            val tokenInfo = mapOf(
                FIRESTORE_LAST_VISIT_KEY to FieldValue.serverTimestamp(),
                FIRESTORE_TOKEN_ID_KEY to token
            )
            // All firestore start from the main thread to avoid concurrency issues.
            applicationScope.launch(mainDispatcher) {
                firebaseFirestore.collection(FIRESTORE_USER_COLLECTION)
                    .document(uid)
                    .collection(FIRESTORE_FCM_TOKEN_COLLECTION)
                    .document(token.take(FIRESTORE_TOKEN_MAX_LENGTH))
                    .set(tokenInfo, SetOptions.merge())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Timber.d("FCM Id token was successfully uploaded: $token")
                        } else {
                            Timber.d("Error while uploading FCM Id token: $token")
                        }
                    }
            }
        } catch (e: Exception) {
            Timber.d(e)
        }
    }
}
