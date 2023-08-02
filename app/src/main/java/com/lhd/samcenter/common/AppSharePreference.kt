package com.lhd.samcenter.common

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class AppSharePreference @Inject constructor(private val context: Context) {
    companion object{
        const val APP_SHARE_KEY = "com.fatherofapps.androidbase"
    }

    private fun getSharedPreferences(): SharedPreferences?{
        return context.getSharedPreferences(APP_SHARE_KEY,Context.MODE_PRIVATE)
    }
}