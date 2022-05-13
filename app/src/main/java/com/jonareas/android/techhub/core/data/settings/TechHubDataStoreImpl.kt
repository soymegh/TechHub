package com.jonareas.android.techhub.core.data.settings

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TechHubDataStoreImpl(context: Context) : SettingsDataStore(context, PREF_FILE),
    TechHubDataStore {

    companion object {
        const val PREF_FILE = "techhub_preferences"
        private val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
        private val AUTH_TOKEN = stringPreferencesKey("token")

    }

    override val isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    override val token: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN] ?: ""
        }

    override suspend fun updateLogStatus(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN]  = isLoggedIn
        }
    }

    override suspend fun updateToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = ""
        }
    }


}