package com.andrdoidlifelang.data.model

data class UserEventResultEntity(
    val userEvents: List<UserEventEntity>? = null,
    val userEventMessage: UserEventMessageEntity? = null
)
