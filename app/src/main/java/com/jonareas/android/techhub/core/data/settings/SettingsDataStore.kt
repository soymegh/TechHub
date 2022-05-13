package com.jonareas.android.techhub.core.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

abstract class SettingsDataStore(context : Context, fileName : String) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(fileName)
    val dataStore : DataStore<Preferences> = context.dataStore
}