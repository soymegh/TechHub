package com.jonareas.android.techhub.core.data.repository.impl

import com.jonareas.android.techhub.core.data.api.response.AuthenticateUserResponse
import com.jonareas.android.techhub.core.data.api.service.LoginService
import com.jonareas.android.techhub.core.data.repository.UserRepository
import com.jonareas.android.techhub.core.presentation.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val service : LoginService) : UserRepository {

    override suspend fun registerUser(user: User) {
        service.registerUser(user.registerRequest)
    }

    override suspend fun authenticateUser(user: User): AuthenticateUserResponse =
        service.authenticateUser(user.authenticateRequest)



}