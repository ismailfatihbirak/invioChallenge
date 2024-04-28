package com.example.inviochallenge.presentation.home

import com.example.inviochallenge.domain.model.Data
import com.example.inviochallenge.domain.model.RoomModel

data class HomeState (
    val isLoading : Boolean = false,
    val unis : List<Data> = listOf(),
    val error : String = "",
    val favUnis : List<RoomModel> = listOf(),
)