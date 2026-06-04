package com.shd.mapbuilder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MapDao {
    @Query("SELECT * FROM map_projects ORDER BY updatedAt DESC")
    fun getAllMaps(): Flow<List<MapProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMap(map: MapProject)

    @Delete
    suspend fun deleteMap(map: MapProject)
}
