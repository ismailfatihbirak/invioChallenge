package com.example.inviochallenge.data.remote

import com.example.inviochallenge.domain.model.Main
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UniversityApi {

    //https://storage.googleapis.com/invio-com/usg-challenge/universities-at-turkey/page-1.json

    @GET("page-{pageNumber}.json")
    suspend fun getUniversities(
        @Path("pageNumber") pageNumber: Int
    ): Main



}