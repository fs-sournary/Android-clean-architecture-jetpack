package com.andrdoidlifelang.domain.model

data class AuthBasicUserInfo(
    val isSignedIn: Boolean,
    val email: String? = null,
    val authUserInfo: List<AuthUserInfo>? = null,
    val lastSignInTimeStamp: Long? = null,
    val creationTimestamp: Long? = null,
    val isAnonymous: Boolean? = null,
    val phoneNumber: String? = null,
    val uid: String? = null,
    val isEmailVerified: Boolean? = null,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val providerId: String? = null
)
