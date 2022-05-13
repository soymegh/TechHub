package com.jonareas.android.techhub.core.data.api.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(
    private val username : String,
    private val password : String,
    private val email : String,
     val enabled : Boolean,
    @SerialName(value = "idRole")
    val role : Int
)