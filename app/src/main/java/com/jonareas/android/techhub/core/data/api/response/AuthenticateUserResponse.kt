package com.jonareas.android.techhub.core.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthenticateUserResponse(
    @SerialName(value = "jwttoken")
    val token : String
)