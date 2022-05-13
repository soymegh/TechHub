package com.jonareas.android.techhub.core.presentation.model

import com.jonareas.android.techhub.core.data.api.request.AuthenticateUserRequest
import com.jonareas.android.techhub.core.data.api.request.RegisterUserRequest

data class User(
   var username: String,
   var password: String,
    var email: String = ""
) {
        val authenticateRequest: AuthenticateUserRequest
            get() = AuthenticateUserRequest(username = username, password = password)

        val registerRequest: RegisterUserRequest
            get() = RegisterUserRequest(username, password, email, enabled =true, role =  1)

}