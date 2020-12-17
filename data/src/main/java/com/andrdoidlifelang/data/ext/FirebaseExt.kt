package com.andrdoidlifelang.data.ext

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Task<T>.suspendAndWait(): T = suspendCancellableCoroutine { continuation ->
    addOnSuccessListener { data ->
        continuation.resume(data)
    }
    addOnFailureListener { exception ->
        continuation.resumeWithException(exception)
    }
    addOnCanceledListener {
        continuation.resumeWithException(Exception("The Firebase Task was cancelled"))
    }
}
