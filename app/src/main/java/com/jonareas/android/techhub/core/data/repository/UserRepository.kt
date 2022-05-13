package com.jonareas.android.techhub.core.data.repository

import com.jonareas.android.techhub.core.data.api.response.AuthenticateUserResponse
import com.jonareas.android.techhub.core.presentation.model.User

interface UserRepository {

    suspend fun registerUser(user : User)

    suspend fun authenticateUser(user : User) : AuthenticateUserResponse

}