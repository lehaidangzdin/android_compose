package com.lhd.samcenter.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private const val ACCESS_TOKEN = "AccessToken"
        private const val REFRESH_TOKEN = "RefreshToken"
        val accessToken = stringPreferencesKey(ACCESS_TOKEN)
        val refreshToken = stringPreferencesKey(REFRESH_TOKEN)
    }

    suspend fun updateAccessToken(token: String) {
        dataStore.edit { preference ->
            preference[accessToken] = token
        }
    }

    fun getAccessToken(): Flow<String> {
        val serialFlow: Flow<String> = dataStore.data.map { preference ->
            preference[accessToken] ?: ""
        }
        return serialFlow
    }

    suspend fun updateRefreshToken(token: String) {
        dataStore.edit { preference ->
            preference[refreshToken] = token
        }
    }

    fun getRefreshToken(): Flow<String> {
        val brandFlow: Flow<String> = dataStore.data.map { preference ->
            preference[refreshToken] ?: ""
        }
        return brandFlow
    }

}