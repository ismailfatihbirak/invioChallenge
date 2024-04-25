package com.example.inviochallenge.domain.use_case.insert_fav_uni

import android.util.Log
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import com.example.inviochallenge.util.Resource
import java.lang.Exception
import javax.inject.Inject

class InsertFavUniUseCase @Inject constructor(private val repository : UniversityRoomRepo) {
    suspend fun executeInsertFavUni (uni : RoomModel) {
        repository.insertFavoriteUni(uni)
    }
}