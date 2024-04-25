package com.example.inviochallenge.domain.use_case.get_fav_uni

import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.RoomModel
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import com.example.inviochallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetFavUniUseCase @Inject constructor(private val repository : UniversityRoomRepo) {
    fun executeGetUniversities () : Flow<Resource<List<RoomModel>>> = flow{
        try {
            emit(Resource.Loading())
            val universityList = repository.getAllFavoriteUni()
            emit(Resource.Success(universityList))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}