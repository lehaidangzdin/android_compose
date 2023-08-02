package com.lhd.samcenter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lhd.samcenter.data.database.daos.CustomerDao
import com.lhd.samcenter.data.database.entities.CustomerEntity

@Database(entities = [CustomerEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun customerDao(): CustomerDao
}