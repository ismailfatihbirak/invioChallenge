package com.example.inviochallenge.domain.repository

import com.example.inviochallenge.domain.model.RoomModel

interface UniversityRoomRepo {
    suspend fun getAllFavoriteUni(): List<RoomModel>

    suspend fun insertFavoriteUni(uni: RoomModel)

    suspend fun deleteFavoriteUni(name: String)

    suspend fun isFavoriteUniExists(name: String): Boolean
}