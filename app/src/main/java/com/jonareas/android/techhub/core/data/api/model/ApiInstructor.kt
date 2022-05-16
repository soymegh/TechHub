package com.jonareas.android.techhub.core.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiInstructor(
    val fullName: String,
    val photoPath: String
)