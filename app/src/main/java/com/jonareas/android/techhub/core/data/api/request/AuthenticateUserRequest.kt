package com.jonareas.android.techhub.core.data.api.request

import kotlinx.serialization.Serializable

@Serializable
class AuthenticateUserRequest(
    private val username : String,
    private val password : String
)