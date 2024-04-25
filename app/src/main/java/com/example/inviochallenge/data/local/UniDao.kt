package com.example.inviochallenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.inviochallenge.domain.model.RoomModel

@Dao
interface UniDao {
    @Query("SELECT * FROM universities")
    suspend fun getAllFavoriteUni(): List<RoomModel>

    @Insert
    suspend fun insertFavoriteUni(uni: RoomModel)

    @Query("DELETE FROM universities WHERE name = :name")
    suspend fun deleteFavoriteUni(name: String)

    @Query("SELECT EXISTS(SELECT 1 FROM universities WHERE name = :name)")
    suspend fun isFavoriteUniExists(name: String): Boolean

}