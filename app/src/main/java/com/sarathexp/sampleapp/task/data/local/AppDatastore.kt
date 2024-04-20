package com.sarathexp.sampleapp.task.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        val LOGGED_IN_KEY = booleanPreferencesKey("logged_in")
    }

    suspend fun getLoggedIn(): Boolean {
        return dataStore.data.map { preferences -> preferences[LOGGED_IN_KEY] }.first() == true
    }

    suspend fun saveLoggedIn(value: Boolean = true) {
        dataStore.edit { preferences -> preferences[LOGGED_IN_KEY] = value }
    }
}
