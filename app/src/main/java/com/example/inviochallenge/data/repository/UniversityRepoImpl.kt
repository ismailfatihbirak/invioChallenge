package com.example.inviochallenge.data.repository

import com.example.inviochallenge.data.remote.UniversityApi
import com.example.inviochallenge.domain.model.Main
import com.example.inviochallenge.domain.repository.UniversityRepo
import javax.inject.Inject

class UniversityRepoImpl @Inject constructor(private val api : UniversityApi)  : UniversityRepo {

    override suspend fun getUniversities(pageNumber: Int): Main {
        return api.getUniversities(pageNumber)
    }
}