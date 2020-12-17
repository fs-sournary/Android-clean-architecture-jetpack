package com.andrdoidlifelang.data.mapper

import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.model.AuthUserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo

fun FirebaseAuth.map(): AuthBasicUserInfo = AuthBasicUserInfo(
    isSignedIn = currentUser != null,
    email = currentUser?.email,
    authUserInfo = currentUser?.providerData?.map(),
    lastSignInTimeStamp = currentUser?.metadata?.lastSignInTimestamp,
    creationTimestamp = currentUser?.metadata?.creationTimestamp,
    isAnonymous = currentUser?.isAnonymous,
    phoneNumber = currentUser?.phoneNumber,
    uid = currentUser?.uid,
    isEmailVerified = currentUser?.isEmailVerified,
    displayName = currentUser?.displayName,
    photoUrl = currentUser?.photoUrl?.toString(),
    providerId = currentUser?.providerId
)

private fun List<UserInfo>?.map(): List<AuthUserInfo>? = this?.map {
    AuthUserInfo(
        uid = it.uid,
        providerId = it.providerId,
        displayName = it.displayName,
        photoUrl = it.photoUrl.toString(),
        email = it.email,
        phoneNumber = it.phoneNumber,
        isEmailVerified = it.isEmailVerified
    )
}
