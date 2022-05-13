package com.jonareas.android.techhub.core.data.api.response

import kotlinx.serialization.SerialName

class AuthenticateUserResponse(
    @SerialName(value = "jwttoken")
    private val token : String
)