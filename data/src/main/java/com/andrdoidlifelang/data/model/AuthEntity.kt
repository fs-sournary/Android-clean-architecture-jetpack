package com.andrdoidlifelang.data.model

import com.andrdoidlifelang.data.mapper.EntityMapper
import com.andrdoidlifelang.domain.model.Auth
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class AuthEntity(
    @SerializedName("id")
    val userId: String,
    val username: String? = null,
    val email: String? = null,
    val token: String? = null
) : ModelEntity()

class AuthEntityMapper @Inject constructor() : EntityMapper<Auth, AuthEntity> {
    override fun mapToData(model: Auth): AuthEntity = AuthEntity(
        userId = model.userId,
        username = model.username,
        email = model.email,
        token = model.token
    )

    override fun mapToDomain(entity: AuthEntity): Auth = Auth(
        userId = entity.userId,
        username = entity.username,
        email = entity.email,
        token = entity.token
    )
}
