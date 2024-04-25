package com.example.inviochallenge.domain.use_case.delete_fav_uni

import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import javax.inject.Inject

class DeleteFavUniUseCase @Inject constructor(private val repository : UniversityRoomRepo) {
    suspend fun executeDeleteFavUni (name: String) {
        repository.deleteFavoriteUni(name)
    }
}