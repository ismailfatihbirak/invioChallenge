package com.example.inviochallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inviochallenge.domain.model.RoomModel

@Database(
    entities = [RoomModel::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun uniDao(): UniDao
}