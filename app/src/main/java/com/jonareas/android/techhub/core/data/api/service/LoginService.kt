package com.jonareas.android.techhub.core.data.api.service

import com.jonareas.android.techhub.core.data.api.request.AuthenticateUserRequest
import com.jonareas.android.techhub.core.data.api.request.RegisterUserRequest
import com.jonareas.android.techhub.core.data.api.response.AuthenticateUserResponse
import com.jonareas.android.techhub.core.data.api.utils.ApiConstants
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {

    @POST(ApiConstants.USERS_END_POINT)
    suspend fun registerUser(@Body registerUserRequest: RegisterUserRequest)

    @POST(ApiConstants.LOGIN_END_POINT)
    suspend fun authenticateUser(authenticateRequest : AuthenticateUserRequest) :
            AuthenticateUserResponse

}