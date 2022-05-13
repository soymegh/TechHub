package com.jonareas.android.techhub.core.data.settings

import kotlinx.coroutines.flow.Flow

interface TechHubDataStore {
    val isLoggedIn: Flow<Boolean>
    val token : Flow<String>
    suspend fun updateLogStatus(isLoggedIn: Boolean)
    suspend fun updateToken(token : String)
    suspend fun clearToken()
}