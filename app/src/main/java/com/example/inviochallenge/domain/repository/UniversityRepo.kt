package com.example.inviochallenge.domain.repository

import com.example.inviochallenge.domain.model.Main

interface UniversityRepo {
    suspend fun getUniversities(pageNumber : Int) : Main
}