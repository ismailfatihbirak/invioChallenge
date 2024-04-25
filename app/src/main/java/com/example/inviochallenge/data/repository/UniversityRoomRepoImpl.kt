package com.example.inviochallenge.data.repository

import com.example.inviochallenge.data.local.UniDao
import com.example.inviochallenge.data.remote.UniversityApi
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.repository.UniversityRepo
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import javax.inject.Inject

class UniversityRoomRepoImpl @Inject constructor(private val dao : UniDao)  : UniversityRoomRepo {
    override suspend fun getAllFavoriteUni(): List<RoomModel> {
        return dao.getAllFavoriteUni()
    }

    override suspend fun insertFavoriteUni(uni: RoomModel) {
        return dao.insertFavoriteUni(uni)
    }

    override suspend fun deleteFavoriteUni(name: String) {
        return dao.deleteFavoriteUni(name)
    }

    override suspend fun isFavoriteUniExists(name: String): Boolean {
        return dao.isFavoriteUniExists(name)
    }
}