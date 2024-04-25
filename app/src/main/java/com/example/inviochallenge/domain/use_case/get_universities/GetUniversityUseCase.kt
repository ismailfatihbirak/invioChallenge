package com.example.inviochallenge.domain.use_case.get_universities

import android.util.Log
import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.Main
import com.example.inviochallenge.domain.repository.UniversityRepo
import com.example.inviochallenge.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetUniversityUseCase @Inject constructor(private val repository : UniversityRepo) {
    fun executeGetUniversities (pageNumber : Int) : Flow<Resource<List<Data>>> = flow{
        try {
            emit(Resource.Loading())
            val universityList = repository.getUniversities(pageNumber)
            if (universityList.currentPage == pageNumber){
                emit(Resource.Success(universityList.data))
            }else{
                emit(Resource.Error("No University found!"))

            }
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}