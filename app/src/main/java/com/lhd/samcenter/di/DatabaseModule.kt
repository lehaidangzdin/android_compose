package com.lhd.samcenter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.lhd.samcenter.common.DataStoreManager
import com.lhd.samcenter.common.dataStore
import com.lhd.samcenter.data.database.AppDatabase
import com.lhd.samcenter.data.database.daos.CustomerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"app_db").build()
    }

    @Provides
    fun provideCustomerDao(appDatabase: AppDatabase): CustomerDao {
        return appDatabase.customerDao()
    }

    @Provides
    @Singleton
    fun provideDataStorePreference(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager(dataStore)
    }

}