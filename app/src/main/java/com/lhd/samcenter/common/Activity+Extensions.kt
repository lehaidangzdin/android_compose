package com.lhd.samcenter.common

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Activity.customApplication: Application
get() = application as Application

const val DATA_STORE_NAME = "APP_DATASTORE"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)