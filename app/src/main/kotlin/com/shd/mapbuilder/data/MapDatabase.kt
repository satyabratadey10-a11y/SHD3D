package com.shd.mapbuilder.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MapProject::class], version = 1, exportSchema = false)
abstract class MapDatabase : RoomDatabase() {
    abstract fun mapDao(): MapDao
}
