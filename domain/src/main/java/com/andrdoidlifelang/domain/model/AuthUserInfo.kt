package com.andrdoidlifelang.domain.model

data class AuthUserInfo(
    val uid: String?,
    val providerId: String?,
    val displayName: String?,
    val photoUrl: String?,
    val email: String?,
    val phoneNumber: String?,
    val isEmailVerified: Boolean?
)
