package com.example.inviochallenge.domain.use_case.is_favorite_exists

import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.repository.UniversityRoomRepo
import com.example.inviochallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class isFavoriteExistsUseCase @Inject constructor(private val repository : UniversityRoomRepo){

    fun executeIsFavoriteExists (name: String) : Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading())
            val favExists = repository.isFavoriteUniExists(name)
            emit(Resource.Success(favExists))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}

