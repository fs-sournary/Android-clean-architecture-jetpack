package com.andrdoidlifelang.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrdoidlifelang.data.model.VideoEntity

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getVideoDao(): VideoDao
}
